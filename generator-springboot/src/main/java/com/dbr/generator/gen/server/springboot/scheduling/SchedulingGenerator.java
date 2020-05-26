package com.dbr.generator.gen.server.springboot.scheduling;

import com.dbr.generator.VelocityUtil;
import com.dbr.generator.gen.AbstractGeneratorJava;
import com.dbr.generator.gen.server.springboot.scheduling.model.Scheduling;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Generiert einen Job anhand der übergebenen Daten.
 */
public class SchedulingGenerator extends AbstractGeneratorJava {

    private String cronExpression;

    public SchedulingGenerator(Scheduling scheduleEntity) {
        super(scheduleEntity.getClazzName(), scheduleEntity.getPackageName());
        this.cronExpression = scheduleEntity.getCron();
    }

    public static void main(String[] args) {
        SchedulingGenerator schedulingGenerator = new SchedulingGenerator(
                new Scheduling(null, "UserJob", "com.dbr.generator", "* * * * 0"));
        String content = schedulingGenerator.create();
        System.out.println(content);
    }

    /**
     * Erstellt Job anhand der übergebenen Werte
     *
     * @return
     * @throws IOException
     */
    @Override
    public String create() {
        VelocityEngine velocityEngine = VelocityUtil.getEngine();

        Template t = velocityEngine.getTemplate("sb-scheduling.vm");

        VelocityContext context = new VelocityContext();
        context.put("clazzSimpleName", getClazzSimpleName());
        context.put("cron", cronExpression);

        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();
    }
}
