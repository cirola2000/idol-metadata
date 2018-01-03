
package org.aksw.idol.metadata.services;

import java.util.Collection;
import java.util.List;

import org.aksw.idol.metadata.models.DistributionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * @author Ciro Baron Neto Sep 11, 2016
 */
@Service
public class DistributionServices {

	final static Logger logger = LoggerFactory.getLogger(DistributionServices.class);

	MongoTemplate template;

	@Autowired
	public DistributionServices(MongoTemplate template) {
		this.template = template;
	}

	public DistributionModel findByURI(String uri) {
		return template.findOne(new Query().addCriteria(Criteria.where(DistributionModel.MAPPING_URI).is(uri)),
				DistributionModel.class);
	}

	protected void upsert(DistributionModel distribution) {

		logger.debug("Upserting distribution: " + distribution.getUri());

		DBObject dbObject = new BasicDBObject();
		template.getConverter().write(distribution, dbObject);
		template.upsert(new Query().addCriteria(Criteria.where(DistributionModel.MAPPING_URI).is(distribution.getUri())),
				Update.fromDBObject(dbObject, DistributionModel.MAPPING_ID), DistributionModel.class);
	}

	public long countAll() {
		return template.findAll(DistributionModel.class).size();
	}

	public void saveDistribution(DistributionModel distribution) {
		upsert(distribution);
	}

	public void saveAllDistributions(Collection<DistributionModel> distributions) {
		for (DistributionModel distribution : distributions) {
			upsert(distribution);
		}
	}

	public Collection<DistributionModel> getDistributions(Collection<String> urls) {
		return template.find(new Query().addCriteria(Criteria.where(DistributionModel.MAPPING_URI).in(urls)),
				DistributionModel.class);
	}

	public DistributionModel getDistributionByURL(String url) {
		return template.findOne(new Query().addCriteria(Criteria.where(DistributionModel.MAPPING_URI).is(url)),
				DistributionModel.class);
	}

	public DistributionModel getDistributionById(String id) {
		return template.findOne(new Query().addCriteria(Criteria.where(DistributionModel.MAPPING_ID).is(id)),
				DistributionModel.class);
	}

	public List<DistributionModel> getDistributions(Boolean vocabularies) {

		Query query = new Query();
		query.addCriteria(Criteria.where(DistributionModel.MAPPING_IS_VOCABULARY).is(vocabularies));
		return template.find(query, DistributionModel.class);
	}

	public List<DistributionModel> getDistributions() {
		Query query = new Query();
		return template.find(query, DistributionModel.class);
	}

	public void removeDistributions(String id) {
		template.remove(new Query().addCriteria(Criteria.where(DistributionModel.MAPPING_ID).is(id)));
	}

	public DistributionModel saveDistribution(String uri, boolean isVocab, String title, String label, String provenance) {

		DistributionModel distribution = new DistributionModel();
		distribution.setUri(uri);
		distribution.setIsVocabulary(isVocab);
		distribution.setTitle(title);
		distribution.setLabel(label);

		upsert(distribution);

		return distribution;
	}

}
