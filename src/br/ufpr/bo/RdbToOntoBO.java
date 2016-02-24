package br.ufpr.bo;

import java.io.IOException;
import java.util.Scanner;

import br.ufpr.bean.Column;
import br.ufpr.bean.Database;
import br.ufpr.bean.Datatype;
import br.ufpr.bean.Table;
import br.ufpr.dao.ColumnDao;
import br.ufpr.dao.DatatypeDao;
import br.ufpr.dao.DatabaseDao;
import br.ufpr.dao.TableDao;
import br.ufpr.form.RdbToOntoForm;

public class RdbToOntoBO {
	
	DatabaseDao databaseDao = new DatabaseDao();
	TableDao tableDao = new TableDao();
	ColumnDao columnDao = new ColumnDao();
	DatatypeDao datatypeDao = new DatatypeDao();
	
	public Database createDatabase(RdbToOntoForm form) {
		
		Database database = new Database();
		database.setName(form.getDatabaseName());
		
		// Salvando o nome do banco de dados na T001.
		databaseDao.saveOrUpdate(database);
		
		try {
			Scanner scanner = new Scanner(form.getDatabaseStructure().getInputStream(), "UTF-8");

	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            //System.out.println(line);
	            
	            String[] fields = line.split(";");
	            
            	// Inserir as tabelas que não são associativas.
	            if (fields[0].equals("T") && fields[6].equals("0")) {
            		System.out.println(line);
            		
            		Table table = new Table();
            		table.setDatabase(database);
            		table.setPhysicalName(fields[1]);
            		table.setLogicalName(fields[2]);
            		table.setDescription(fields[4]);
            		table.setAssociative(false); //fields[6];
            		
            		tableDao.saveOrUpdate(table);
            	}
	        }
	        
	        /*
	         scanner = new Scanner(form.getDatabaseStructure().getInputStream(), "UTF-8");
	         
	         while (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            //System.out.println(line);
	            
	            String[] fields = line.split(";");
	            
            	// Inserir as tabelas associativas.
	            if (fields[0].equals("T") && fields[6].equals("1")) {
            		System.out.println(line);
            		
            		Table table = new Table();
            		table.setDatabase(database);
            		table.setPhysicalName(fields[1]);
            		table.setLogicalName(fields[2]);
            		table.setDescription(fields[4]);
            		table.setAssociative(true); //fields[6];
            		
            		tableDao.saveOrUpdate(table);
            	}
	        }*/
	        
	        scanner.close();
	        
	        scanner = new Scanner(form.getDatabaseStructure().getInputStream(), "UTF-8");
	        
	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            String[] fields = line.split(";");
	            
            	// Inserir as colunas.
	            // Se tem indAssociativeKey = 0 e IndColumnCheck = 0.
	            if ("C".equals(fields[0]) && "0".equals(fields[13]) && "0".equals(fields[18])) {
            		System.out.println(line);
            		
            		Column column = new Column();
            		
            		column.setPhysicalName(fields[1]);
            		column.setLogicalName(fields[2]);
            		column.setLogicalName2(fields[3]);
            		
            		Table table = tableDao.getByPhysicalName(database.getId(), fields[7]);
            		column.setTable(table);
            		
            		// Se informou o datatype.
            		if (!"".equals(fields[8])) {
	            		Datatype datatype = datatypeDao.getByDescription(fields[8]);
	            		
	            		// Se encontrou o datatype no banco.
	            		if (datatype != null) {
	            			column.setDatatype(datatype);
	            		}
	            		else {
	            			// Deve cadastrar o datatype.
	            			datatype = new Datatype();
	            			datatype.setDescription(fields[8].toLowerCase());
	            			datatypeDao.saveOrUpdate(datatype);
	            			column.setDatatype(datatype);
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
            		
            		//fields[19] Não existe a coluna.
            		//fields[20] Não existe a coluna.
            		
            		columnDao.saveOrUpdate(column);
            	}
	        }
	        
	        scanner.close();
	        
	        scanner = new Scanner(form.getDatabaseStructure().getInputStream(), "UTF-8");
	        
	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            String[] fields = line.split(";");
	            
            	// Inserir as colunas.
	            // Se tem indAssociativeKey = 1 e IndColumnCheck = 0.
	            if ("C".equals(fields[0]) && "1".equals(fields[13]) && "0".equals(fields[18])) {
            		System.out.println(line);
            		
            		Column column = new Column();
            		
            		column.setPhysicalName(fields[1]);
            		column.setLogicalName(fields[2]);
            		column.setLogicalName2(fields[3]);
            		
            		Table table = tableDao.getByPhysicalName(database.getId(), fields[7]);
            		column.setTable(table);
            		
            		// Se informou o datatype.
            		if (!"".equals(fields[8])) {
	            		Datatype datatype = datatypeDao.getByDescription(fields[8]);
	            		
	            		// Se encontrou o datatype no banco.
	            		if (datatype != null) {
	            			column.setDatatype(datatype);
	            		}
	            		else {
	            			// Deve cadastrar o datatype.
	            			datatype = new Datatype();
	            			datatype.setDescription(fields[8].toLowerCase());
	            			datatypeDao.saveOrUpdate(datatype);
	            			column.setDatatype(datatype);
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
            		
            		//fields[19] Não existe a coluna.
            		//fields[20] Não existe a coluna.
            		
            		columnDao.saveOrUpdate(column);
            	}
	        }
	        
	        scanner.close();
	        
	        scanner = new Scanner(form.getDatabaseStructure().getInputStream(), "UTF-8");
	        
	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            String[] fields = line.split(";");
	            
            	// Inserir as colunas.
	            // Se tem IndColumnCheck = 1.
	            if ("C".equals(fields[0]) && "1".equals(fields[18])) {
            		System.out.println(line);
            		
            		Column column = new Column();
            		
            		column.setPhysicalName(fields[1]);
            		column.setLogicalName(fields[2]);
            		column.setLogicalName2(fields[3]);
            		
            		Table table = tableDao.getByPhysicalName(database.getId(), fields[7]);
            		column.setTable(table);
            		
            		// Se informou o datatype.
            		if (!"".equals(fields[8])) {
	            		Datatype datatype = datatypeDao.getByDescription(fields[8]);
	            		
	            		// Se encontrou o datatype no banco.
	            		if (datatype != null) {
	            			column.setDatatype(datatype);
	            		}
	            		else {
	            			// Deve cadastrar o datatype.
	            			datatype = new Datatype();
	            			datatype.setDescription(fields[8].toLowerCase());
	            			datatypeDao.saveOrUpdate(datatype);
	            			column.setDatatype(datatype);
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
            		
            		//fields[19] Não existe a coluna.
            		//fields[20] Não existe a coluna.
            		
            		columnDao.saveOrUpdate(column);
            	}
	        }
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//DatabaseDomain databaseDomain = new DatabaseDomain();
		//databaseDomain.setDescription("financeiro");
		//databaseDomain.setDatabase(database);
		//databaseDao.saveOrUpdate(databaseDomain);
		
		return database;
	}
}