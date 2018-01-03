package com.github.cirola2000.idol.metadata.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "dataset")
public class DatasetModel {
	
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

	@NotNull(message = "Provenance must not be empty!") 
	Collection<String> provenance = new ArrayList<>();
 
	Collection<String> parentDatasets = new ArrayList<>();

	Collection<String> subsetIds = new ArrayList<>();

	Collection<String> distributionIds = new ArrayList<>(); 

	@Field("isVocabulary")	
	boolean isVocabulary = false;

	String title;

	String label;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public void setId(String id) {
		this.id = new ObjectId(id);
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Collection<String> getProvenance() {
		return provenance;
	}

	public void setProvenance(Collection<String> provenance) {
		this.provenance = provenance;
	}

	public void addProvenance(String provenance) {
		this.provenance.add(provenance);
	}

	public Collection<String> getParentDatasets() {
		return parentDatasets;
	}

	public void setParentDatasets(Collection<String> parentDatasets) {
		this.parentDatasets = parentDatasets;
	}

	public Collection<String> getSubsetIds() {
		return subsetIds;
	}

	public void setSubsetIds(Collection<String> subsetIds) {
		this.subsetIds = subsetIds;
	}

	public Collection<String> getDistributionIds() {
		return distributionIds;
	}

	public void setDistributionIds(Collection<String> distributionIds) {
		this.distributionIds = distributionIds;
	}

	public void addDistributionId(String distributionId) {
		this.distributionIds.add(distributionId);
	}

	public boolean isVocabulary() {
		return isVocabulary;
	}

	public void setVocabulary(boolean isVocabulary) {
		this.isVocabulary = isVocabulary;
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

}
