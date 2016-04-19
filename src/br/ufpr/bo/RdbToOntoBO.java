package br.ufpr.bo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import br.ufpr.bean.CheckSubject;
import br.ufpr.bean.CheckValue;
import br.ufpr.bean.Column;
import br.ufpr.bean.ColumnCheckValue;
import br.ufpr.bean.ColumnToDatatypeProperty;
import br.ufpr.bean.Database;
import br.ufpr.bean.DatabaseDomain;
import br.ufpr.bean.DatatypeDb;
import br.ufpr.bean.DatatypeOnto;
import br.ufpr.bean.DatatypeProperty;
import br.ufpr.bean.Hierarchy;
import br.ufpr.bean.Instance;
import br.ufpr.bean.Ontology;
import br.ufpr.bean.Table;
import br.ufpr.bean.TableDatabaseDomain;
import br.ufpr.dao.CheckSubjectDao;
import br.ufpr.dao.CheckValueDao;
import br.ufpr.dao.ClassDao;
import br.ufpr.dao.ColumnCheckValueDao;
import br.ufpr.dao.ColumnDao;
import br.ufpr.dao.ColumnToDatatypePropertyDao;
import br.ufpr.dao.DatabaseDao;
import br.ufpr.dao.DatabaseDomainDao;
import br.ufpr.dao.DatatypeDbDao;
import br.ufpr.dao.DatatypeOntoDao;
import br.ufpr.dao.DatatypePropertyDao;
import br.ufpr.dao.HierarchyDao;
import br.ufpr.dao.InstanceDao;
import br.ufpr.dao.OntologyDao;
import br.ufpr.dao.TableDao;
import br.ufpr.dao.TableDatabaseDomainDao;
import br.ufpr.form.RdbToOntoForm;
import br.ufpr.util.Util;

public class RdbToOntoBO {

	DatabaseDao databaseDao = new DatabaseDao();
	TableDao tableDao = new TableDao();
	ColumnDao columnDao = new ColumnDao();
	DatatypeDbDao datatypeDbDao = new DatatypeDbDao();
	DatatypeOntoDao datatypeOntoDao = new DatatypeOntoDao();
	CheckSubjectDao checkSubjectDao = new CheckSubjectDao();
	CheckValueDao checkValueDao = new CheckValueDao();
	ColumnCheckValueDao columnCheckValueDao = new ColumnCheckValueDao();
	DatabaseDomainDao databaseDomainDao = new DatabaseDomainDao();
	TableDatabaseDomainDao tableDatabaseDomainDao = new TableDatabaseDomainDao();
	OntologyDao ontologyDao = new OntologyDao();
	ClassDao classDao = new ClassDao();
	HierarchyDao hierarchyDao = new HierarchyDao();
	InstanceDao instanceDao = new InstanceDao();
	DatatypePropertyDao datatypePropertyDao = new DatatypePropertyDao();
	ColumnToDatatypePropertyDao columnToDatatypePropertyDao = new ColumnToDatatypePropertyDao();

	/**
	 * 
	 * @param form
	 * @return
	 */
	public Database importFile(RdbToOntoForm form) {

		Database database = new Database();
		Ontology ontology = null;

		database.setName(form.getDatabaseName().toLowerCase());

		// Salvando o nome do banco de dados na T001.
		databaseDao.saveOrUpdate(database);

		try {
			// Chama a função que importa a primeira ontologia na T016.
			ontology = importOntology(database);
		}
		catch (Exception e1) {
			e1.printStackTrace();
			return null;
		}

		try {
			// Chama a função que importa as tabelas na T002.
			importTables(form, database);
		}
		catch (Exception e1) {
			e1.printStackTrace();
			return null;
		}

		try {
			// Chama a função que importa as colunas na T003.
			importColumns(form, database);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		try {
			// Chama a função que importa os valores na T011.
			importClass(database, ontology);
		}
		catch (Exception e1) {
			e1.printStackTrace();
			return null;
		}

		try {
			// Chama a função que importa os valores na T015.
			importInstance();
		}
		catch (Exception e1) {
			e1.printStackTrace();
			return null;
		}

		try {
			// Chama a função que importa os valores na T013.
			importDatatypeProperty(database, ontology);
		}
		catch (Exception e1) {
			e1.printStackTrace();
			return null;
		}

		return database;
	}

	/**
	 * 
	 * @param database
	 */
	public Ontology importOntology(Database database) {
		Ontology ontology = new Ontology();
		ontology.setName(database.getName());
		ontology.setDatabase(database);
		ontologyDao.saveOrUpdate(ontology);
		return ontology;
	}

	/**
	 * 
	 * @param form
	 * @param database
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void importTables(RdbToOntoForm form, Database database) throws FileNotFoundException, IOException {
		Scanner scanner = new Scanner(form.getDatabaseStructure().getInputStream(), "UTF-8");

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();

			String[] fields = line.split(";", -1);

			// Inserir as tabelas que não são associativas.
			if (fields[0].equals("T") && fields[6].equals("0")) {
				System.out.println(line);

				Table table = new Table();
				table.setDatabase(database);
				table.setPhysicalName(fields[1]);
				table.setLogicalName(fields[2]);
				table.setDescription(fields[4]);
				table.setAssociative("1".equals(fields[6]) ? true : false);

				// Cadastrando na T002.
				tableDao.saveOrUpdate(table);

				DatabaseDomain databaseDomain = databaseDomainDao.getByDescription(database, fields[5]);

				// Se não encontrou o databaseDomain (T008) no banco, deve cadastrá-lo.
				if (databaseDomain == null) {
					databaseDomain = new DatabaseDomain();
					databaseDomain.setDatabase(database);
					databaseDomain.setDescription(fields[5].toLowerCase());
					databaseDomainDao.saveOrUpdate(databaseDomain);
				}

				TableDatabaseDomain tableDatabaseDomain = new TableDatabaseDomain();
				tableDatabaseDomain.setTable(table);
				tableDatabaseDomain.setDatabaseDomain(databaseDomain);

				// Cadastrando o relacionamento na T010.
				tableDatabaseDomainDao.saveOrUpdate(tableDatabaseDomain);
			}
		}

		scanner.close();
	}

	/**
	 * 
	 * @param form
	 * @param database
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void importColumns(RdbToOntoForm form, Database database) throws FileNotFoundException, IOException {
		Scanner scanner = new Scanner(form.getDatabaseStructure().getInputStream(), "UTF-8");

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] fields = line.split(";", -1);

			// Inserir as colunas.
			// Se tem indAssociativeKey = 0 e IndColumnCheck = 0.
			if ("C".equals(fields[0]) && "0".equals(fields[13]) && "0".equals(fields[18])) {
				System.out.println(line);
				importColumn(database, fields);
			}
		}

		scanner.close();

		scanner = new Scanner(form.getDatabaseStructure().getInputStream(), "UTF-8");

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] fields = line.split(";", -1);

			// Inserir as colunas.
			// Se tem indAssociativeKey = 1 e IndColumnCheck = 0.
			if ("C".equals(fields[0]) && "1".equals(fields[13]) && "0".equals(fields[18])) {
				System.out.println(line);
				importColumn(database, fields);
			}
		}

		scanner.close();

		scanner = new Scanner(form.getDatabaseStructure().getInputStream(), "UTF-8");

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] fields = line.split(";", -1);

			// Inserir as colunas.
			// Se tem IndColumnCheck = 1.
			if ("C".equals(fields[0]) && "1".equals(fields[18])) {
				System.out.println(line);
				importColumn(database, fields);
			}
		}

		scanner.close();
	}

	/**
	 * 
	 * @param database
	 * @param fields
	 */
	public void importColumn(Database database, String[] fields) {
		Column column = new Column();

		column.setPhysicalName(fields[1]);
		column.setLogicalName(fields[2]);

		// Se o logical name vier vazio, coloco o physical name em seu lugar.
		if (column.getLogicalName() == null || "".equals(column.getLogicalName())) {
			column.setLogicalName(Util.funcaoMaiuscula(column.getPhysicalName()));			
		}

		column.setLogicalName2(fields[3]);

		Table table = tableDao.getByPhysicalName(database.getId(), fields[7]);

		// table será null quando o sistema não encontrar a tabela com a qual a coluna importada é relacionada.
		// Isso pode ocorrer quando a tabela for associativa, pois no momento essas tabelas n�o est�o sendo importadas.
		if (table == null) {
			return;
		}

		column.setTable(table);

		// Se informou o datatype.
		if (!"".equals(fields[8])) {
			DatatypeDb datatypeDb = datatypeDbDao.getByDescription(fields[8]);

			// Se encontrou o datatype no banco.
			if (datatypeDb != null) {
				column.setDatatypeDb(datatypeDb);
			}
			else {
				// Deve cadastrar o datatype (T005).
				datatypeDb = new DatatypeDb();
				datatypeDb.setDescription(fields[8].toLowerCase());
				datatypeDbDao.saveOrUpdate(datatypeDb);
				column.setDatatypeDb(datatypeDb);

				// Clonar o datatype na T018.
				DatatypeOnto datatypeOnto = new DatatypeOnto();
				datatypeOnto.setDescription(datatypeDb.getDescription());
				datatypeOnto.setDatatypeDb(datatypeDb);
				datatypeOntoDao.saveOrUpdate(datatypeOnto);
			}
		}

		column.setPrimaryKey("1".equals(fields[9]) ? true : false);
		column.setForeignKey("1".equals(fields[10]) ? true : false);

		Table fkTable = tableDao.getByPhysicalName(database.getId(), fields[11]);
		column.setFkTable(fkTable);

		column.setIndDescription("1".equals(fields[12]) ? true : false);
		column.setIndAssociativeKey("1".equals(fields[13]) ? true : false);

		Table akTableId1 = tableDao.getByPhysicalName(database.getId(), fields[14]);
		column.setAkTableId1(akTableId1);

		if (akTableId1 != null) {
			Column akColumnId1 = columnDao.getByPhysicalName(akTableId1.getId(), fields[15]);
			column.setAkColumnId1(akColumnId1);
		}

		Table akTableIdN = tableDao.getByPhysicalName(database.getId(), fields[16]);
		column.setAkTableIdN(akTableIdN);

		if (akTableIdN != null) {
			Column akColumnN = columnDao.getByPhysicalName(akTableIdN.getId(), fields[17]);
			column.setAkColumnN(akColumnN);
		}

		column.setIndColumnCheck("1".equals(fields[18]) ? true : false);

		// Salvando a coluna (T003).
		columnDao.saveOrUpdate(column);

		// Se informou o checkSubject.
		if (!"".equals(fields[21])) {
			CheckSubject checkSubject = checkSubjectDao.getByDescription(fields[21]);

			// Se não encontrou o checkSubject no banco, deve cadastrá-lo. 
			if (checkSubject == null) {
				checkSubject = new CheckSubject();
				checkSubject.setDescription(fields[21].toLowerCase());
				// Inserindo na T007.
				checkSubjectDao.saveOrUpdate(checkSubject);
			}

			String[] checkValues = fields[19].split(",", -1);
			String[] checkAbreviations = fields[20].split(",", -1);

			for (int j = 0; j < checkValues.length; j++) {

				// Verificar se um checkValue com essa description + abreviation já existe na T006.
				CheckValue checkValueAux = checkValueDao.getByDescriptionAndAbreviation(checkValues[j], checkAbreviations[j]);

				// Se não existir, deve ser cadastrado.
				if (checkValueAux == null) {
					CheckValue checkValue = new CheckValue();
					checkValue.setDescription(checkValues[j]);
					checkValue.setAbreviation(checkAbreviations[j]);
					checkValue.setCheckSubject(checkSubject);
					// Inserindo na T006.
					checkValueDao.saveOrUpdate(checkValue);

					// Inserindo na T009.
					ColumnCheckValue columnCheckValue = new ColumnCheckValue();
					columnCheckValue.setColumn(column);
					columnCheckValue.setCheckValue(checkValue);
					columnCheckValueDao.saveOrUpdate(columnCheckValue);
				}
			}
		}
	}

	/**
	 * Cadastra os valores na T011.
	 * 
	 * @param database
	 * @param ontology
	 */
	public void importClass(Database database, Ontology ontology) {
		// Chama a função que importa a class Thing na T011.
		br.ufpr.bean.Class clazz = importClassThing(ontology);

		// Chama a função que importa os valores de database domain (T008) na T011.
		importClassDatabaseDomain(database, clazz, ontology);

		// Chama a função que importa os valores de tables (T002) na T011.
		importClassTable(database, clazz, ontology);

		// Chama a função que importa os valores de check subject (T007) na T011.
		importClassCheckSubject(database, clazz, ontology);
	}

	/**
	 * 
	 * @param ontology
	 * @return
	 */
	public br.ufpr.bean.Class importClassThing(Ontology ontology) {
		br.ufpr.bean.Class c = new br.ufpr.bean.Class();
		c.setName("Thing");
		c.setOntology(ontology);
		classDao.saveOrUpdate(c);
		return c;
	}

	/**
	 * Busca todos os registros da T008 relacionados a determinado banco de dados.
	 * Para cada um dos registros encontrados, insere um registro na T011.
	 * Al�m disso, insere um registro na T012 relacionando o registro rec�m-inserido na T011 e a Thing.
	 * 
	 * @param database
	 * @param clazz
	 * @param ontology
	 */
	public void importClassDatabaseDomain(Database database, br.ufpr.bean.Class clazz, Ontology ontology) {
		List<DatabaseDomain> lista = databaseDomainDao.getByDatabase(database);

		if (lista == null || lista.size() == 0) {
			return;
		}

		String name;

		for (DatabaseDomain databaseDomain : lista) {
			br.ufpr.bean.Class c = new br.ufpr.bean.Class();
			c.setDatabaseDomain(databaseDomain);
			name = Util.funcaoMaiuscula(databaseDomain.getDescription());
			name = "g" + name;
			c.setName(name);
			c.setOntology(ontology);
			classDao.saveOrUpdate(c);

			Hierarchy hierarchy = new Hierarchy();
			hierarchy.setSuperClass(clazz);
			hierarchy.setSubClass(c);

			hierarchyDao.saveOrUpdate(hierarchy);
		}
	}

	/**
	 * Busca todos os registros da T002 relacionados a determinado banco de dados.
	 * Para cada um dos registros encontrados, insere um registro na T011.
	 * Al�m disso, insere um registro na T012 relacionando o registro rec�m-inserido na T011 e a Thing.
	 * 
	 * @param database
	 * @param clazz
	 * @param ontology
	 */
	public void importClassTable(Database database, br.ufpr.bean.Class clazz, Ontology ontology) {
		List<Table> lista = tableDao.getByDatabase(database);

		if (lista == null || lista.size() == 0) {
			return;
		}

		String name;

		for (Table table : lista) {
			br.ufpr.bean.Class c = new br.ufpr.bean.Class();
			c.setTable(table);
			name = Util.funcaoMaiuscula(table.getLogicalName());

			if (name == null || "".equals(name)) {
				name = Util.funcaoMaiuscula(table.getPhysicalName());
			}

			name = "e" + name;
			c.setName(name);
			c.setOntology(ontology);
			classDao.saveOrUpdate(c);

			Hierarchy hierarchy = new Hierarchy();
			hierarchy.setSuperClass(clazz);
			hierarchy.setSubClass(c);

			hierarchyDao.saveOrUpdate(hierarchy);
		}
	}

	/**
	 * Busca todos os registros da T007 que ainda não estão na T011.
	 * Para cada um dos registros encontrados, insere um registro na T011.
	 * Além disso, insere um registro na T012 relacionando o registro recém-inserido na T011 e a Thing.
	 * 
	 * @param database
	 * @param clazz
	 * @param ontology
	 */
	public void importClassCheckSubject(Database database, br.ufpr.bean.Class clazz, Ontology ontology) {
		// Busca todos os itens da T007.
		List<CheckSubject> lista = checkSubjectDao.getAll();

		if (lista == null || lista.size() == 0) {
			return;
		}

		String name;
		br.ufpr.bean.Class clazzAux;

		for (CheckSubject checkSubject : lista) {
			// Busca na T011 se o item da T007 existe.
			clazzAux = classDao.getByCheckSubject(checkSubject);

			// Se não existe na T011, deve cadastrar.
			if (clazzAux == null) {
				br.ufpr.bean.Class c = new br.ufpr.bean.Class();
				c.setCheckSubject(checkSubject);
				name = Util.funcaoMaiuscula(checkSubject.getDescription());
				name = "v" + name;
				c.setName(name);
				c.setOntology(ontology);
				classDao.saveOrUpdate(c);

				Hierarchy hierarchy = new Hierarchy();
				hierarchy.setSuperClass(clazz);
				hierarchy.setSubClass(c);

				hierarchyDao.saveOrUpdate(hierarchy);
			}
		}
	}

	/**
	 * 
	 */
	public void importInstance() {
		// Buscar todos os itens da T011 que contenham algum valor na C007.
		List<br.ufpr.bean.Class> lista = classDao.getWhenCheckSubjectNotNull();

		if (lista == null || lista.size() == 0) {
			return;
		}

		Instance instance;
		String description;

		// Para cada item encontrado na T011, verificar na T015 se ele existe (através do C011_CLASS_ID).
		for (br.ufpr.bean.Class clazz : lista) {

			instance = instanceDao.getByClass(clazz);

			// Se não existir nenhum registro na T015 relacionado ao C011 passado, deve ser inserido.
			if (instance == null) {
				// Buscar na T006 quais os valores relacionados a esse C007.
				List<CheckValue> checkValues = checkValueDao.getByCheckSubject(clazz.getCheckSubject());

				if (checkValues == null || checkValues.size() == 0) {
					continue;
				}

				// Para cada registro encontrado, inserir um valor na T015.
				for (CheckValue checkValue : checkValues) {
					Instance newIntance = new Instance();

					description = clazz.getName();
					description = description + "_" + Util.funcaoMaiuscula(checkValue.getDescription());
					newIntance.setDescription(description);

					newIntance.setClazz(clazz);
					newIntance.setOntology(clazz.getOntology());
					instanceDao.saveOrUpdate(newIntance);
				}
			}
		}
	}

	/**
	 * 
	 */
	public void importDatatypeProperty(Database database, Ontology ontology) {
		// Colunas com C003_IND_DESCRIPTION = 1 e C003_IND_COLUMN_CHECK = 0.
		importDatatypeProperty01(database, ontology);

		// Colunas com C003_IND_DESCRIPTION = 0 e C003_IND_COLUMN_CHECK = 0.
		importDatatypeProperty02(database, ontology);
	}

	/**
	 * 
	 * @param database
	 * @param ontology
	 */
	public void importDatatypeProperty01(Database database, Ontology ontology) {
		// Buscar todas as colunas do database passado com C003_IND_DESCRIPTION = 1 e C003_IND_COLUMN_CHECK = 0.
		List<Column> columns = columnDao.getByIndDescriptionAndIndColumnCheck(database.getId(), true, false);  

		if (columns == null || columns.size() == 0) {
			return;
		}

		String description;

		for (Column column : columns) {
			DatatypeProperty datatypeProperty = new DatatypeProperty();			
			description = "a" + Util.funcaoMaiuscula(column.getLogicalName());
			datatypeProperty.setDescription(description);
			datatypeProperty.setOntology(ontology);

			DatatypeOnto datatypeOnto = datatypeOntoDao.getByDatatypeDb(column.getDatatypeDb());

			if (datatypeOnto != null) {
				datatypeProperty.setDatatypeOnto(datatypeOnto);

				// Verificando se já existe uma coluna com esse nome registrado na T013.
				// Se já existir, não precisa cadastrar novamente. Apenas vincular na T020.
				DatatypeProperty datatypePropertyExistente = datatypePropertyDao.getByDatatypeProperty(datatypeProperty); 

				if (datatypePropertyExistente == null) {
					// Inserir na T013.
					datatypePropertyDao.saveOrUpdate(datatypeProperty);

					// Inserir na T020.
					ColumnToDatatypeProperty columnToDatatypeProperty = new ColumnToDatatypeProperty();
					columnToDatatypeProperty.setColumn(column);
					columnToDatatypeProperty.setDatatypeProperty(datatypeProperty);
					columnToDatatypePropertyDao.saveOrUpdate(columnToDatatypeProperty);
				}
				else {
					// Inserir na T020.
					ColumnToDatatypeProperty columnToDatatypeProperty = new ColumnToDatatypeProperty();
					columnToDatatypeProperty.setColumn(column);
					columnToDatatypeProperty.setDatatypeProperty(datatypePropertyExistente);
					columnToDatatypePropertyDao.saveOrUpdate(columnToDatatypeProperty);
				}
			}
		}
	}

	/**
	 * 
	 * @param database
	 * @param ontology
	 */
	public void importDatatypeProperty02(Database database, Ontology ontology) {
		// Buscar todas as colunas do database passado com C003_IND_DESCRIPTION = 0 e C003_IND_COLUMN_CHECK = 0.
		List<Column> columns = columnDao.getByIndDescriptionAndIndColumnCheck(database.getId(), false, false);  

		if (columns == null || columns.size() == 0) {
			return;
		}

		String description;

		for (Column column : columns) {
			DatatypeProperty datatypeProperty = new DatatypeProperty();			
			description = "a" + Util.funcaoMaiuscula(column.getLogicalName());
			datatypeProperty.setDescription(description);
			datatypeProperty.setOntology(ontology);

			DatatypeOnto datatypeOnto = datatypeOntoDao.getByDatatypeDb(column.getDatatypeDb());

			if (datatypeOnto != null) {
				datatypeProperty.setDatatypeOnto(datatypeOnto);

				// Inserir na T013.
				datatypePropertyDao.saveOrUpdate(datatypeProperty);

				// Inserir na T020.
				ColumnToDatatypeProperty columnToDatatypeProperty = new ColumnToDatatypeProperty();
				columnToDatatypeProperty.setColumn(column);
				columnToDatatypeProperty.setDatatypeProperty(datatypeProperty);
				columnToDatatypePropertyDao.saveOrUpdate(columnToDatatypeProperty);
			}
		}
	}
}