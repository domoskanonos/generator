package com.dbr.generator.sample;

import com.dbr.generator.gen.server.db.TableGenerator;
import com.dbr.generator.gen.server.db.model[0].Database;
import com.dbr.generator.gen.server.db.model[0].TableVM;
import com.dbr.generator.gen.server.dto.DTOGenerator;
import com.dbr.generator.gen.server.dto.model[0].DTOVM;
import com.dbr.generator.gen.server.entity.EntityGenerator;
import com.dbr.generator.gen.server.entity.model[0].EntityVM;
import com.dbr.generator.sample.dto.UserDTO;
import com.dbr.generator.sample.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class Playground {

    private static final Logger log = LoggerFactory.getLogger(Playground.class);
    public static final String EXAMPLE_PACKAGE = "com.dbr.generator.result.entity";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        entityFromClazzExample();
        dtoFromClazzExample();
        entityFromTableExample();
    }

    private static void dtoFromClazzExample() {
        DTOGenerator dtoGenerator = new DTOGenerator(new DTOVM(UserEntity.class));
        String content = dtoGenerator.create();
        log.info(content);
    }

    private static void entityFromTableExample() throws SQLException, ClassNotFoundException {
        Database database = new Database("com.dbr.generator", "oracle.jdbc.driver.OracleDriver",
                "jdbc:oracle:thin:@localhost:scheme", "username", "password");
        TableVM tableVM = new TableVM(EXAMPLE_PACKAGE, "MyTable", database);
        TableGenerator tableGenerator = new TableGenerator(tableVM);
        String content = tableGenerator.create();
        log.info(content);
    }

    private static void entityFromClazzExample() {
        EntityVM entityVM = new EntityVM(EXAMPLE_PACKAGE, "UserSample", UserDTO.class);
        EntityGenerator entityGenerator = new EntityGenerator(entityVM);
        String content = entityGenerator.create();
        log.info(content);
    }

}
