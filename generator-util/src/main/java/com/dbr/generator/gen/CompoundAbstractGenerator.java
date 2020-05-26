package com.dbr.generator.gen;

import com.dbr.generator.gen.server.db.TableGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
@AllArgsConstructor
public abstract class CompoundAbstractGenerator {

    protected static final Logger log = LoggerFactory.getLogger(TableGenerator.class);

    protected String basePackageName;

    public abstract void writeDown() throws Exception;

}
