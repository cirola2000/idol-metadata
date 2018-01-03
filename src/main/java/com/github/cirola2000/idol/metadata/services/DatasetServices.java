/**
 * 
 */
package com.github.cirola2000.idol.metadata.services;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.github.cirola2000.idol.metadata.models.DatasetModel;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * @author Ciro Baron Neto Sep 11, 2016
 */
@Service
public class DatasetServices {

	final static Logger logger = LoggerFactory.getLogger(DatasetServices.class);

	MongoTemplate template;

	@Autowired
	public DatasetServices(MongoTemplate template) {
		this.template = template;
	}

	public DatasetModel findByURI(String uri) {
		return template.findOne(new Query().addCriteria(Criteria.where(DatasetModel.MAPPING_URI).is(uri)),
				DatasetModel.class);
	}

	protected void upsert(DatasetModel dataset) {

		logger.debug("Upserting dataset: " + dataset.getUri());

		DBObject dbObject = new BasicDBObject();
		template.getConverter().write(dataset, dbObject);
		template.upsert(new Query().addCriteria(Criteria.where(DatasetModel.MAPPING_URI).is(dataset.getUri())),
				Update.fromDBObject(dbObject, DatasetModel.MAPPING_ID), DatasetModel.class);
	}

	public long countAll() {
		return template.findAll(DatasetModel.class).size();
	}

	public void saveDataset(DatasetModel dataset) {
		upsert(dataset);
	}

	public void saveAllDatasets(Collection<DatasetModel> datasets) {
		for (DatasetModel dataset : datasets) {
			upsert(dataset);
		}
	}

	public Collection<DatasetModel> getDatasets(Collection<String> urls) {
		return template.find(new Query().addCriteria(Criteria.where(DatasetModel.MAPPING_URI).in(urls)),
				DatasetModel.class);
	}

	public DatasetModel getDatasetByURL(String url) {
		return template.findOne(new Query().addCriteria(Criteria.where(DatasetModel.MAPPING_URI).is(url)),
				DatasetModel.class);
	}

	public DatasetModel getDatasetById(String id) {
		return template.findOne(new Query().addCriteria(Criteria.where(DatasetModel.MAPPING_ID).is(id)),
				DatasetModel.class);
	}

	public List<DatasetModel> getDatasets(Boolean vocabularies) {

		Query query = new Query();
		query.addCriteria(Criteria.where(DatasetModel.MAPPING_IS_VOCABULARY).is(vocabularies));
		return template.find(query, DatasetModel.class);
	}

	public List<DatasetModel> getDatasets() {
		Query query = new Query();
		return template.find(query, DatasetModel.class);
	}

	public void removeDataset(String id) {
		template.remove(new Query().addCriteria(Criteria.where(DatasetModel.MAPPING_ID).is(id)));
	}

	public DatasetModel saveDataset(String uri, boolean isVocab, String title, String label, String provenance) {

		DatasetModel dataset = new DatasetModel();
		dataset.setUri(uri);
		dataset.setVocabulary(isVocab);
		dataset.setTitle(title);
		dataset.setLabel(label);
		dataset.addProvenance(provenance);

		upsert(dataset);

		return dataset;
	}

}
