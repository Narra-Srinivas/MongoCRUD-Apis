package com.remodelai.mongofileservice.repo;

import com.remodelai.mongofileservice.model.PdfFile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PdfFileRepository extends MongoRepository<PdfFile, String> {
}