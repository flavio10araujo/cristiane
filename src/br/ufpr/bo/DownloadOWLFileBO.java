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
		
		/*header.append("<?xml version=\"1.0\"?>"
				+ "<!DOCTYPE rdf:RDF ["
				+ "<!ENTITY owl \"http://www.w3.org/2002/07/owl#\" >"
				+ "<!ENTITY xsd \"http://www.w3.org/2001/XMLSchema#\" >"
				+ "<!ENTITY rdfs \"http://www.w3.org/2000/01/rdf-schema#\" >"
				+ "<!ENTITY rdf \"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" >"
				+ "<!ENTITY untitled-ontology-131 \"http://www.semanticweb.org/alex/ontologies/2015/3/untitled-ontology-131#\" >"
				+ "]>"
				+ "<rdf:RDF xmlns=\"http://www.semanticweb.org/alex/ontologies/2015/3/untitled-ontology-131#\" "
				+ "xml:base=\"http://www.semanticweb.org/alex/ontologies/2015/3/untitled-ontology-131\" "
				+ "xmlns:untitled-ontology-131=\"http://www.semanticweb.org/alex/ontologies/2015/3/untitled-ontology-131#\" "
				+ "xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\" "
				+ "xmlns:owl=\"http://www.w3.org/2002/07/owl#\" "
				+ "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\" "
				+ "xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">");*/
		
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
		//footer.append("</rdf:RDF>");
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
			file.append("<Class IRI=\"#");file.append(hierarchy.getSuperClass().getName());file.append("\" />");
			file.append("<Class IRI=\"#");file.append(hierarchy.getSubClass().getName());file.append("\" />");
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
    		file.append("<DataPropertyDomain>");
    		file.append("<DataProperty IRI=\"#");file.append(datatypeProperty.getDescription());file.append("\" />");
    		
    		datatypePropertyDomain = datatypePropertyDomainDao.getByDatatypePropertyID(datatypeProperty.getId()).get(0);
    		file.append("<Class IRI=\"#");file.append(datatypePropertyDomain.getClassDomain().getName());file.append("\" />");
    		
    		file.append("</DataPropertyDomain>");
    	}
		
		return file;
	}
	
	public StringBuffer setDataPropertyDomain02() {
		StringBuffer file = new StringBuffer();
		
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
    		}
    		
    		file.append("</ObjectUnionOf>");
    		file.append("</DataPropertyDomain>");
    	}

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
    	}
		
		List<DatatypePropertyDomain> datatypePropertyDomainList = null; 
		
		for (DatatypeProperty datatypeProperty : datatypePropertyList) {
			
    		datatypePropertyDomainList = datatypePropertyDomainDao.getByDatatypePropertyID(datatypeProperty.getId());
    		
    		for (DatatypePropertyDomain datatypePropertyDomain : datatypePropertyDomainList) {
    			file.append("<DataPropertyDomain>");
        		file.append("<DataProperty IRI=\"#");file.append(datatypeProperty.getDescription() + "_" + datatypePropertyDomain.getClassDomain().getName());file.append("\" />");
    			file.append("<Class IRI=\"#");file.append(datatypePropertyDomain.getClassDomain().getName());file.append("\" />");
    			file.append("</DataPropertyDomain>");
    			
    			file.append("<SubDataPropertyOf>");
    			file.append("<DataProperty IRI=\"#");file.append(datatypeProperty.getDescription() + "_" + datatypePropertyDomain.getClassDomain().getName());file.append("\" />");
    			file.append("<DataProperty IRI=\"#");file.append(datatypeProperty.getDescription());file.append("\" />");
    			file.append("</SubDataPropertyOf>");
    		}
    	}
		
		return file;
	}
	
	public StringBuffer setObjectProperty() {
		StringBuffer file = new StringBuffer();
		
		//file.append("<Declaration><ObjectProperty IRI=\"#leciona\"/></Declaration>");
		//file.append("<ObjectPropertyDomain><ObjectProperty IRI=\"#leciona\" /><Class IRI=\"#Professor\" /></ObjectPropertyDomain>");
		//file.append("<ObjectPropertyRange><ObjectProperty IRI=\"#leciona\" /><Class IRI=\"#Modulo\" /></ObjectPropertyRange>");
		List<ObjectProperty> objectPropertyList = objectPropertyDao.listAll();
		ObjectPropertyDomainRange objectPropertyDomainRange = null;
		
		for (ObjectProperty objectProperty : objectPropertyList) {
			file.append("<Declaration>");
			file.append("<ObjectProperty IRI=\"#");
			file.append(objectProperty.getDescription());
			file.append("\"/></Declaration>");
			
			objectPropertyDomainRange = objectPropertyDomainRangeDao.findByObjectProperty(objectProperty);
			
			//TODO - retirar esse IF depois de terminar o passo 31
			if (objectPropertyDomainRange.getClassDomain() != null) {
			file.append("<ObjectPropertyDomain>");
			file.append("<ObjectProperty IRI=\"#");
			file.append(objectProperty.getDescription());
			file.append("\" /><Class IRI=\"#");
			file.append(objectPropertyDomainRange.getClassDomain().getName());
			file.append("\" /></ObjectPropertyDomain>");
			}
			
			if (objectPropertyDomainRange.getClassRange() != null) {
				file.append("<ObjectPropertyRange>");
				file.append("<ObjectProperty IRI=\"#");
				file.append(objectProperty.getDescription());
				file.append("\" /><Class IRI=\"#");
				file.append(objectPropertyDomainRange.getClassRange().getName());
				file.append("\" /></ObjectPropertyRange>");
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