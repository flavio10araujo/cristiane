package br.ufpr.bo;

import java.sql.SQLException;
import java.util.List;

import br.ufpr.bean.DatatypeProperty;
import br.ufpr.bean.DatatypePropertyDomain;
import br.ufpr.bean.Hierarchy;
import br.ufpr.bean.Instance;
import br.ufpr.bean.ObjectProperty;
import br.ufpr.bean.ObjectPropertyDomainRange;
import br.ufpr.dao.ClassDao;
import br.ufpr.dao.DatatypePropertyDao;
import br.ufpr.dao.DatatypePropertyDomainDao;
import br.ufpr.dao.HierarchyDao;
import br.ufpr.dao.InstanceDao;
import br.ufpr.dao.ObjectPropertyDao;
import br.ufpr.dao.ObjectPropertyDomainRangeDao;
import br.ufpr.util.Util;

public class DownloadOWLFileBO {

	ClassDao classDao = new ClassDao();
	HierarchyDao hierarchyDao = new HierarchyDao();
	DatatypePropertyDao datatypePropertyDao = new DatatypePropertyDao();
	DatatypePropertyDomainDao datatypePropertyDomainDao = new DatatypePropertyDomainDao();
	ObjectPropertyDao objectPropertyDao = new ObjectPropertyDao();
	ObjectPropertyDomainRangeDao objectPropertyDomainRangeDao = new ObjectPropertyDomainRangeDao();
	InstanceDao instanceDao = new InstanceDao();

	/**
	 * Função utilizada para gerar o arquivo OWL.
	 * 
	 * @return
	 * @throws SQLException
	 */
	public StringBuffer downloadOWLFile() throws SQLException {
		StringBuffer file = createOWLHeader();
		file.append(setDeclaration());
		file.append(setSubClassOf());
		file.append(setDisjointClasses());
		file.append(setDataPropertyDomain());
		file.append(setObjectProperty());
		file.append(setInstance());

		return file.append(createOWLFooter());
	}
	
	public StringBuffer createOWLHeader() {
		StringBuffer header = new StringBuffer();
		header.append("<?xml version=\"1.0\"?>"
				+ "<Ontology xmlns=\"http://www.w3.org/2002/07/owl#\" "
				+ "xml:base=\"http://www.semanticweb.org/home/ontologies/2016/4/untitled-ontology-24\" "
				+ "xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" "
				+ "xmlns:xml=\"http://www.w3.org/XML/1998/namespace\" "
				+ "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\" "
				+ "xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\" "
				+ "ontologyIRI=\"http://www.semanticweb.org/home/ontologies/2016/4/untitled-ontology-24\"> "
				+ "<Prefix name=\"owl\" IRI=\"http://www.w3.org/2002/07/owl#\" />"
				+ "<Prefix name=\"rdf\" IRI=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" />"
				+ "<Prefix name=\"xml\" IRI=\"http://www.w3.org/XML/1998/namespace\" />"
				+ "<Prefix name=\"xsd\" IRI=\"http://www.w3.org/2001/XMLSchema#\" />"
				+ "<Prefix name=\"rdfs\" IRI=\"http://www.w3.org/2000/01/rdf-schema#\" />");
		
		return header;
	}
	
	public StringBuffer createOWLFooter() {
		StringBuffer footer = new StringBuffer();
		footer.append("</Ontology>");
		return footer;
	}
	
	public StringBuffer setDeclaration() {
		StringBuffer file = new StringBuffer();
		
		// Deve ser gerada uma Classe para todos os registros da T011.
		//file.append("<Declaration><Class IRI=\"#universityStudent\" /></Declaration>");
		List<br.ufpr.bean.Class> classList = classDao.listAll();
		
		for (br.ufpr.bean.Class clazz : classList) {
			file.append("<Declaration><Class IRI=\"#");
			file.append(clazz.getName());
			file.append("\" /></Declaration>");
		}
		
		return file;
	}
	
	public StringBuffer setSubClassOf() {
		StringBuffer file = new StringBuffer();
		
		// Para cada classe gerada, é preciso informar de quem esta classe é uma subclasse.
		// Para isso, deve ser identificado na T012 todos os C011_SUPER_CLASS_ID, pesquisando pelo código desta classe na coluna C011_SUB_CLASS_ID.
		//file.append("<SubClassOf><Class IRI=\"#universityStudent\"/><Class IRI=\"#student\"/></SubClassOf>");
		List<Hierarchy> hierarchyList = hierarchyDao.listAll();
		
		for (Hierarchy hierarchy : hierarchyList) {
			file.append("<SubClassOf>");
			file.append("<Class IRI=\"#");file.append(hierarchy.getSubClass().getName());file.append("\" />");
			file.append("<Class IRI=\"#");file.append(hierarchy.getSuperClass().getName());file.append("\" />");
			file.append("</SubClassOf>");
		}
		
		return file;
	}
	
	public StringBuffer setDisjointClasses() {
		StringBuffer file = new StringBuffer();
		
		List<br.ufpr.bean.Class> classList = classDao.listAll();
		
		for (br.ufpr.bean.Class classThis : classList) {
			file.append("<DisjointClasses>");
			file.append("<Class IRI=\"#");file.append(classThis.getName());file.append("\" />");
			
			for (br.ufpr.bean.Class classOther : classList) {
				if (classThis.getId() != classOther.getId()) {
					file.append("<Class IRI=\"#");file.append(classOther.getName());file.append("\" />");
				}
			}
			
			file.append("</DisjointClasses>");
		}
		
		return file;
	}
	
	public StringBuffer setDataPropertyDomain() {
		StringBuffer file = new StringBuffer();
		file.append(setDataPropertyDomain01()); 
		file.append(setDataPropertyDomain02());
		file.append(setDataPropertyDomain03());
		return file;
	}
	
	public StringBuffer setDataPropertyDomain01() {
		StringBuffer file = new StringBuffer();
		
		// Para gerar o data property deve ser processado todos os registros da T013, em que (C013_IND_COMMON_CONCEPT = 0 AND C013_IND_DESCRIPTION = 0). 
		//file.append("<DataPropertyDomain><DataProperty IRI=\"#matricula_aluno\" /><Class IRI=\"#Aluno\" /></DataPropertyDomain>");
    	List<DatatypeProperty> datatypePropertyList = datatypePropertyDao.getByIndCommonConceptAndIndDescription(false, false);
    	DatatypePropertyDomain datatypePropertyDomain = null; 
    	
    	for (DatatypeProperty datatypeProperty : datatypePropertyList) {
    		//file.append("<Declaration><DataProperty IRI=\"#matricula_aluno\" /></Declaration>");
    		file.append("<Declaration><DataProperty IRI=\"#");file.append(datatypeProperty.getDescription());file.append("\" /></Declaration>");
    		
    		file.append("<DataPropertyDomain>");
    		file.append("<DataProperty IRI=\"#");file.append(datatypeProperty.getDescription());file.append("\" />");
    		datatypePropertyDomain = datatypePropertyDomainDao.getByDatatypePropertyID(datatypeProperty.getId()).get(0);
    		file.append("<Class IRI=\"#");file.append(datatypePropertyDomain.getClassDomain().getName());file.append("\" />");
    		file.append("</DataPropertyDomain>");
    		
    		//file.append("<DataPropertyRange><DataProperty IRI=\"#matricula_aluno\" /><Datatype abbreviatedIRI=\"xsd:integer\" /></DataPropertyRange>");
    		file.append("<DataPropertyRange><DataProperty IRI=\"#");
    		file.append(datatypeProperty.getDescription());
    		file.append("\" /><Datatype abbreviatedIRI=\"xsd:");
    		file.append(datatypeProperty.getDatatypeOnto().getDescription());
    		file.append("\" /></DataPropertyRange>");
    	}
		
		return file;
	}
	
	public StringBuffer setDataPropertyDomain02() {
		StringBuffer file = new StringBuffer();
		StringBuffer file02 = new StringBuffer();
		
		// Para os registros da T013, em que (C013_IND_COMMON_CONCEPT = 1), o resultado da pesquisa na T014 retorne mais que 1 resultado.
		//file.append("<DataPropertyDomain><DataProperty IRI=\"#primeiro_nome\" /><ObjectUnionOf><Class IRI=\"#Pessoa\" /><Class IRI=\"#Portugues\" /></ObjectUnionOf></DataPropertyDomain>");
		List<DatatypeProperty> datatypePropertyList = datatypePropertyDao.getByIndCommonConcept(true);
		List<DatatypePropertyDomain> datatypePropertyDomainList = null; 
		
		for (DatatypeProperty datatypeProperty : datatypePropertyList) {
    		file.append("<DataPropertyDomain>");
    		file.append("<DataProperty IRI=\"#");file.append(datatypeProperty.getDescription());file.append("\" />");
    		file.append("<ObjectUnionOf>");
    		
    		datatypePropertyDomainList = datatypePropertyDomainDao.getByDatatypePropertyID(datatypeProperty.getId());
    		for (DatatypePropertyDomain datatypePropertyDomain : datatypePropertyDomainList) {
    			file.append("<Class IRI=\"#");file.append(datatypePropertyDomain.getClassDomain().getName());file.append("\" />");
    			
    			//file02.append("<Declaration><DataProperty IRI=\"#primeiro_nome\" /></Declaration>");
    			file02.append("<Declaration><DataProperty IRI=\"#");file02.append(datatypePropertyDomain.getClassDomain().getName());file02.append("\" /></Declaration>");
    		}
    		
    		file.append("</ObjectUnionOf>");
    		file.append("</DataPropertyDomain>");
    		
    		//file.append("<DataPropertyRange><DataProperty IRI=\"#primeiro_nome\" /><Datatype abbreviatedIRI=\"xsd:strinh\" /></DataPropertyRange>");
    		file.append("<DataPropertyRange><DataProperty IRI=\"#");
    		file.append(datatypeProperty.getDescription());
    		file.append("\" /><Datatype abbreviatedIRI=\"xsd:");
    		file.append(datatypeProperty.getDatatypeOnto().getDescription());
    		file.append("\" /></DataPropertyRange>");
    	}

		file.append(file02);
		
		return file;
	}
	
	public StringBuffer setDataPropertyDomain03() {
		StringBuffer file = new StringBuffer();
		
		// Para os registros da T013, em que (C013_IND_DESCRIPTION = 1).
		//file.append("<DataPropertyDomain><DataProperty IRI=\"#descricao\" /><Class IRI=\"#Thing\" /></DataPropertyDomain>");
		List<DatatypeProperty> datatypePropertyList = datatypePropertyDao.getByIndDescription(true);
		
		for (DatatypeProperty datatypeProperty : datatypePropertyList) {
    		file.append("<DataPropertyDomain>");
    		file.append("<DataProperty IRI=\"#");file.append(datatypeProperty.getDescription());file.append("\" />");
    		file.append("<Class IRI=\"#Thing\" />");
    		file.append("</DataPropertyDomain>");
    		
    		//file.append("<Declaration><DataProperty IRI=\"#descricao\" /></Declaration>");
    		file.append("<Declaration><DataProperty IRI=\"#");file.append(datatypeProperty.getDescription());file.append("\" /></Declaration>");
    		
    		//file.append("<DataPropertyRange><DataProperty IRI=\"#descricao\" /><Datatype abbreviatedIRI=\"xsd:string\" /></DataPropertyRange>");
    		file.append("<DataPropertyRange><DataProperty IRI=\"#");
    		file.append(datatypeProperty.getDescription());
    		file.append("\" /><Datatype abbreviatedIRI=\"xsd:");
    		file.append(datatypeProperty.getDatatypeOnto().getDescription());
    		file.append("\" /></DataPropertyRange>");
    	}
		
		List<DatatypePropertyDomain> datatypePropertyDomainList = null; 
		
		for (DatatypeProperty datatypeProperty : datatypePropertyList) {
			
    		datatypePropertyDomainList = datatypePropertyDomainDao.getByDatatypePropertyID(datatypeProperty.getId());
    		
    		for (DatatypePropertyDomain datatypePropertyDomain : datatypePropertyDomainList) {
    			//file.append("<Declaration><DataProperty IRI=\"#descricao_grupo\" /></Declaration>");
    			file.append("<Declaration><DataProperty IRI=\"#");file.append(datatypeProperty.getDescription() + "_" + datatypePropertyDomain.getClassDomain().getName());file.append("\" /></Declaration>");
    			
    			file.append("<DataPropertyDomain>");
        		file.append("<DataProperty IRI=\"#");file.append(datatypeProperty.getDescription() + "_" + datatypePropertyDomain.getClassDomain().getName());file.append("\" />");
    			file.append("<Class IRI=\"#");file.append(datatypePropertyDomain.getClassDomain().getName());file.append("\" />");
    			file.append("</DataPropertyDomain>");
    			
    			file.append("<SubDataPropertyOf>");
    			file.append("<DataProperty IRI=\"#");file.append(datatypeProperty.getDescription() + "_" + datatypePropertyDomain.getClassDomain().getName());file.append("\" />");
    			file.append("<DataProperty IRI=\"#");file.append(datatypeProperty.getDescription());file.append("\" />");
    			file.append("</SubDataPropertyOf>");
    			
    			//file.append("<DataPropertyRange><DataProperty IRI=\"#descricao_Grupo\" /><Datatype abbreviatedIRI=\"xsd:string\" /></DataPropertyRange>");
    			file.append("<DataPropertyRange><DataProperty IRI=\"#");
    			file.append(datatypeProperty.getDescription() + "_" + datatypePropertyDomain.getClassDomain().getName());
    			file.append("\" /><Datatype abbreviatedIRI=\"xsd:");
    			file.append("string");
    			file.append("\" /></DataPropertyRange>");
    		}
    	}
		
		return file;
	}
	
	public StringBuffer setObjectProperty() {
		StringBuffer file = new StringBuffer();
		file.append(setObjectProperty01()); 
		file.append(setObjectProperty02());
		return file;
	}
	
	public StringBuffer setObjectProperty01() {
		StringBuffer file = new StringBuffer();
		
		// C019_IND_INVERSE_FUNCTIONAL = 0
		
		//file.append("<Declaration><ObjectProperty IRI=\"#leciona\"/></Declaration>");
		//file.append("<ObjectPropertyDomain><ObjectProperty IRI=\"#leciona\" /><Class IRI=\"#Professor\" /></ObjectPropertyDomain>");
		//file.append("<ObjectPropertyRange><ObjectProperty IRI=\"#leciona\" /><Class IRI=\"#Modulo\" /></ObjectPropertyRange>");
		List<ObjectProperty> objectPropertyList = objectPropertyDao.getByIndInverseFunctional(false);
		ObjectPropertyDomainRange objectPropertyDomainRange = null;
		
		for (ObjectProperty objectProperty : objectPropertyList) {
			file.append("<Declaration>");
			file.append("<ObjectProperty IRI=\"#");
			file.append(objectProperty.getDescription());
			file.append("\"/></Declaration>");
			
			//file.append("<InverseObjectProperties><ObjectProperty IRI=\"#temRestricaoSexo\" /><ObjectProperty IRI=\"#eRestricaoSexoDe\" /></InverseObjectProperties>");
			file.append("<InverseObjectProperties><ObjectProperty IRI=\"#");
			file.append(objectProperty.getDescription());
			file.append("\" /><ObjectProperty IRI=\"#");
			file.append(Util.functionForInverseObjectProperties(objectProperty.getDescription()));
			file.append("\" /></InverseObjectProperties>");
			
			//file.append("<Declaration><ObjectProperty IRI=\"#eRestricaoSexoDe\" /></Declaration>");
			file.append("<Declaration><ObjectProperty IRI=\"#");
			file.append(Util.functionForInverseObjectProperties(objectProperty.getDescription()));
			file.append("\" /></Declaration>");
			
			objectPropertyDomainRange = objectPropertyDomainRangeDao.findByObjectProperty(objectProperty);
			
			//TODO - retirar esse IF depois de terminar o passo 31
			if (objectPropertyDomainRange.getClassDomain() != null) {
			file.append("<ObjectPropertyDomain><ObjectProperty IRI=\"#");
			file.append(objectProperty.getDescription());
			file.append("\" /><Class IRI=\"#");
			file.append(objectPropertyDomainRange.getClassDomain().getName());
			file.append("\" /></ObjectPropertyDomain>");
			
			//file.append("<ObjectPropertyDomain><ObjectProperty IRI=\"#eRestricaoSexoDe\" /><Class IRI=\"#Sexo\" /></ObjectPropertyDomain>");
			file.append("<ObjectPropertyDomain><ObjectProperty IRI=\"#");
			file.append(Util.functionForInverseObjectProperties(objectProperty.getDescription()));
			file.append("\" /><Class IRI=\"#");
			file.append(objectPropertyDomainRange.getClassRange().getName());
			file.append("\" /></ObjectPropertyDomain>");
			}
			
			if (objectPropertyDomainRange.getClassRange() != null) {
				
				// Se C019_MIN_CARDINALITY = 0
				if (!objectProperty.getMinCardinality()) {
					file.append("<ObjectPropertyRange><ObjectProperty IRI=\"#");
					file.append(objectProperty.getDescription());
					file.append("\" /><Class IRI=\"#");
					file.append(objectPropertyDomainRange.getClassRange().getName());
					file.append("\" /></ObjectPropertyRange>");
					
					//file.append("<ObjectPropertyRange><ObjectProperty IRI=\"#eRestricaoSexoDe\" /><Class IRI=\"#eCid\" /></ObjectPropertyRange>");
					file.append("<ObjectPropertyRange><ObjectProperty IRI=\"#");
					file.append(Util.functionForInverseObjectProperties(objectProperty.getDescription()));
					file.append("\" /><Class IRI=\"#");
					file.append(objectPropertyDomainRange.getClassDomain().getName());
					file.append("\" /></ObjectPropertyRange>");
				}
				// Se C019_MIN_CARDINALITY = 1
				else {
					//file.append("<ObjectPropertyRange><ObjectProperty IRI=\"#temCodigodoProcedimento\" /><ObjectMinCardinality cardinality=\"1\"><ObjectProperty IRI=\"#temCodigodoProcedimento\" /><Class IRI=\"#eProcedimento\" /></ObjectMinCardinality></ObjectPropertyRange>");
					file.append("<ObjectPropertyRange><ObjectProperty IRI=\"#");
					file.append(objectProperty.getDescription());
					file.append("\" /><ObjectMinCardinality cardinality=\"1\"><ObjectProperty IRI=\"#");
					file.append(objectProperty.getDescription());
					file.append("\" /><Class IRI=\"#");
					file.append(objectPropertyDomainRange.getClassRange().getName());
					file.append("\" /></ObjectMinCardinality></ObjectPropertyRange>");
					
					file.append("<ObjectPropertyRange><ObjectProperty IRI=\"#");
					file.append(objectProperty.getDescription());
					file.append("\" /><ObjectMinCardinality cardinality=\"1\"><ObjectProperty IRI=\"#");
					file.append(Util.functionForInverseObjectProperties(objectProperty.getDescription()));
					file.append("\" /><Class IRI=\"#");
					file.append(objectPropertyDomainRange.getClassRange().getName());
					file.append("\" /></ObjectMinCardinality></ObjectPropertyRange>");
				}
			}
		}
		
		return file;
	}
	
	public StringBuffer setObjectProperty02() {
		StringBuffer file = new StringBuffer();
		
		// C019_IND_INVERSE_FUNCTIONAL = 1
		
		List<ObjectProperty> objectPropertyList = objectPropertyDao.getByIndInverseFunctional(true);
		ObjectPropertyDomainRange objectPropertyDomainRange = null;
		
		for (ObjectProperty objectProperty : objectPropertyList) {
		
			//file.append("<Declaration><ObjectProperty IRI=\"#PKCodigodoProcedimento\" /></Declaration>");
			file.append("<Declaration><ObjectProperty IRI=\"#");
			file.append(objectProperty.getDescription());
			file.append("\"/></Declaration>");
	
			//file.append("<InverseFunctionalObjectProperty><ObjectProperty IRI=\"#PKCodigodoProcedimento\" /></InverseFunctionalObjectProperty>");
			file.append("<InverseFunctionalObjectProperty><ObjectProperty IRI=\"#");
			file.append(objectProperty.getDescription());
			file.append("\"/></InverseFunctionalObjectProperty>");
	
			objectPropertyDomainRange = objectPropertyDomainRangeDao.findByObjectProperty(objectProperty);		
			
			if (objectPropertyDomainRange.getClassRange() != null) {
				//file.append("<ObjectPropertyRange><ObjectProperty IRI=\"#PKCodigodoProcedimento\" /><ObjectMinCardinality cardinality=\"1\"><ObjectProperty IRI=\"#PKCodigodoProcedimento\" /><Class IRI=\"#eProcedimento\" /></ObjectMinCardinality></ObjectPropertyRange>");
				file.append("<ObjectPropertyRange><ObjectProperty IRI=\"#");
				file.append(objectProperty.getDescription());
				file.append("\" /><ObjectMinCardinality cardinality=\"1\"><ObjectProperty IRI=\"#");
				file.append(objectProperty.getDescription());
				file.append("\" /><Class IRI=\"#");
				file.append(objectPropertyDomainRange.getClassRange().getName());
				file.append("\" /></ObjectMinCardinality></ObjectPropertyRange>");
			}
		}
		
		return file;
	}
	
	public StringBuffer setInstance() {
		StringBuffer file = new StringBuffer();
		
		//file.append("<Declaration><NamedIndividual IRI=\"#M101\" /></Declaration>");
		//file.append("<ClassAssertion><Class IRI=\"#Matematica\" /><NamedIndividual IRI=\"#M101\" /></ClassAssertion>");
		List<Instance> instanceList = instanceDao.listAll();
		
		for (Instance instance : instanceList) {
			file.append("<Declaration><NamedIndividual IRI=\"#");
			file.append(instance.getDescription());
			file.append("\" /></Declaration>");
			
			file.append("<ClassAssertion><Class IRI=\"#");
			file.append(instance.getClazz().getName());
			file.append("\" /><NamedIndividual IRI=\"#");
			file.append(instance.getDescription());
			file.append("\" /></ClassAssertion>");
		}
		
		return file;
	}
}