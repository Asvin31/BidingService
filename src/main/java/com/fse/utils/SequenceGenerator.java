package com.fse.utils;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.fse.model.CustomSequence;

@Service
public class SequenceGenerator {
	@Autowired
	private MongoOperations mongoOperations;

	public long getSequence(String seqName) {
		Query query = new Query(Criteria.where("id").is(seqName));
		Update update = new Update().inc("seq", 1);
		CustomSequence counter = mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(true),
				CustomSequence.class);
		return !Objects.isNull(counter) ? counter.getSeq() : 001;
	}
}
