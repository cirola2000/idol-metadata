package com.github.cirola2000.idol.metadata.models;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "distribution")
public class DistributionModel {
	
	public final static String MAPPING_URI = "uri";
	public final static String MAPPING_ID = "_id";
	public final static String MAPPING_IS_VOCABULARY = "isVocabulary";

	@Id
	@Field("_id")
	private ObjectId id;

	@Indexed(unique = true)
	@Field("uri")
	@NotEmpty(message = "URI must not  be empty!")
	String uri;

	public String downloadUrl;

	public String topDataset;

	public String topDatasetTitle;

	public DistributionStatus status;

	public String lastMsg;

	public String httpByteSize;

	public String httpFormat;

	public String httpLastModified;

	public String format;

	public String resourceUri;

	public String defaultDatasets;

	public String repository;

	public String datasource;

	public String lastTimeStreamed;

	@Field("isVocabulary")
	public Boolean isVocabulary;

	public Long blankNodes;

	public String title;

	public String label;

	public Long triples;

	public Long literals;

	public String sparqlGraph;

	public String sparqlCount;

	public String sparqlEndpoint;

	public enum DistributionStatus {

		STREAMING,

		STREAMED,

		SEPARATING_SUBJECTS_AND_OBJECTS,

		WAITING_TO_STREAM,

		CREATING_BLOOM_FILTER,

		CREATING_LINKSETS,

		ERROR,

		DONE,

		CREATING_JACCARD_SIMILARITY,

		UPDATING_LINK_STRENGTH
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getTopDataset() {
		return topDataset;
	}

	public void setTopDataset(String topDataset) {
		this.topDataset = topDataset;
	}

	public String getTopDatasetTitle() {
		return topDatasetTitle;
	}

	public void setTopDatasetTitle(String topDatasetTitle) {
		this.topDatasetTitle = topDatasetTitle;
	}

	public DistributionStatus getStatus() {
		return status;
	}

	public void setStatus(DistributionStatus status) {
		this.status = status;
	}

	public String getLastMsg() {
		return lastMsg;
	}

	public void setLastMsg(String lastMsg) {
		this.lastMsg = lastMsg;
	}

	public String getHttpByteSize() {
		return httpByteSize;
	}

	public void setHttpByteSize(String httpByteSize) {
		this.httpByteSize = httpByteSize;
	}

	public String getHttpFormat() {
		return httpFormat;
	}

	public void setHttpFormat(String httpFormat) {
		this.httpFormat = httpFormat;
	}

	public String getHttpLastModified() {
		return httpLastModified;
	}

	public void setHttpLastModified(String httpLastModified) {
		this.httpLastModified = httpLastModified;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getResourceUri() {
		return resourceUri;
	}

	public void setResourceUri(String resourceUri) {
		this.resourceUri = resourceUri;
	}

	public String getDefaultDatasets() {
		return defaultDatasets;
	}

	public void setDefaultDatasets(String defaultDatasets) {
		this.defaultDatasets = defaultDatasets;
	}

	public String getRepository() {
		return repository;
	}

	public void setRepository(String repository) {
		this.repository = repository;
	}

	public String getDatasource() {
		return datasource;
	}

	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}

	public String getLastTimeStreamed() {
		return lastTimeStreamed;
	}

	public void setLastTimeStreamed(String lastTimeStreamed) {
		this.lastTimeStreamed = lastTimeStreamed;
	}

	public Boolean getIsVocabulary() {
		return isVocabulary;
	}

	public void setIsVocabulary(Boolean isVocabulary) {
		this.isVocabulary = isVocabulary;
	}

	public Long getBlankNodes() {
		return blankNodes;
	}

	public void setBlankNodes(Long blankNodes) {
		this.blankNodes = blankNodes;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Long getTriples() {
		return triples;
	}

	public void setTriples(Long triples) {
		this.triples = triples;
	}

	public Long getLiterals() {
		return literals;
	}

	public void setLiterals(Long literals) {
		this.literals = literals;
	}

	public String getSparqlGraph() {
		return sparqlGraph;
	}

	public void setSparqlGraph(String sparqlGraph) {
		this.sparqlGraph = sparqlGraph;
	}

	public String getSparqlCount() {
		return sparqlCount;
	}

	public void setSparqlCount(String sparqlCount) {
		this.sparqlCount = sparqlCount;
	}

	public String getSparqlEndpoint() {
		return sparqlEndpoint;
	}

	public void setSparqlEndpoint(String sparqlEndpoint) {
		this.sparqlEndpoint = sparqlEndpoint;
	}

	public static String getMappingUri() {
		return MAPPING_URI;
	}

	public static String getMappingId() {
		return MAPPING_ID;
	}

	public static String getMappingIsVocabulary() {
		return MAPPING_IS_VOCABULARY;
	}
	

}
