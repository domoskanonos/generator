package .ItemDTO;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.math.BigDecimal;

@Entity
@Table(name = "COM_.DBR_.GENERATOR_.BASIC_.ITEM_.DTO")
@Data
@ToString
@Builder(toBuilder = true)
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class com.dbr.generator.basic.item.dto {
    
    @Column(name = "PROJECT_PATH", nullable = false )
    private String projectPath;
    
    @Column(name = "JAVA_CLAZZ_NAME", nullable = false )
    private String javaClazzName;
    
    @Column(name = "JAVA_ID_CLAZZ_SIMPLE_NAME", nullable = false )
    private String javaIdClazzSimpleName;
    
    @Column(name = "PROPERTIES", nullable = false )
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true )
    private java.util.List properties;

}