export class Property {
    id : number = 0;
    name : string = '';
    deactivated : boolean = false;
    propertyType : PropertyType | undefined = undefined;
    propertyTypeName : string = '';
    idProperty : boolean = false;
    mainProperty : boolean = false;
    nullable : boolean = false;
    useJPAIdClazz : boolean = false;
    length : number = 0;
}
export class Item {
    id : number = 0;
    name : string = '';
    deactivated : boolean = false;
    idPropertyType : PropertyType | undefined = undefined;
    template : Template[] = [];
    properties : Property[] = [];
}
export class Project {
    id : number = 0;
    items : Item[] = [];
    template : Template[] = [];
    technicalDescriptor : string = '';
    namespace : string = '';
    deactivated : boolean = false;
}
export class Process {
    id : number = 0;
    projects : Project[] = [];
    processTempPath : string = '';
    processParentPath : string = '';
    technicalDescriptor : string = '';
    deactivated : boolean = false;
}
export enum PropertyType {
    BYTE_ARRAY,
    STRING,
    INTEGER,
    BOOLEAN,
    BIG_DECIMAL,
    LONG,
    FLOAT,
    SHORT,
    DOUBLE,
    TYPE_CHAR,
    DATE,
    DATE_ISO8601,
    ARRAY_STRING,
    ARRAY_ENUMERATION,
    LIST,
    SET,
    ENUMERATION,
    OBJECT,
}
export enum LanguageType {
    JAVA,
    TYPESCRIPT,
    JSON,
}
export enum Template {
    PROJECT_TYPESCRIPT_NIDOCA_I18N,
    PROJECT_TYPESCRIPT_NIDOCA_INDEX,
    PROJECT_TYPESCRIPT_NIDOCA_SERVICE_PAGE,
    PROJECT_TYPESCRIPT_NIDOCA_PAGE_DEFAULT,
    ITEM_JAVA_DTO_TEMPLATE,
    ITEM_JAVA_ENTITY_TEMPLATE,
    ITEM_JAVA_CLAZZ_MAPPING_TEMPLATE,
    ITEM_JAVA_SPRINGBOOT_JPA_REPOSITORY_TEMPLATE,
    ITEM_JAVA_SPRINGBOOT_JPA_SERVICE_BASIC_TEMPLATE,
    ITEM_JAVA_SPRINGBOOT_JPA_SERVICE_SEARCH_TEMPLATE,
    ITEM_JAVA_SPRINGBOOT_REST_CONTROLLER_BASIC_TEMPLATE,
    ITEM_JAVA_SPRINGBOOT_REST_CONTROLLER_SEARCH_TEMPLATE,
    ITEM_TYPESCRIPT_MODEL_TEMPLATE,
    ITEM_TYPESCRIPT_MODEL_ENUM_TEMPLATE,
    ITEM_TYPESCRIPT_REMOTE_REPOSITORY,
    ITEM_TYPESCRIPT_REMOTE_SERVICE,
    ITEM_TYPESCRIPT_NIDOCA_PAGE_EDIT,
    ITEM_TYPESCRIPT_NIDOCA_PAGE_LIST,
    ITEM_TYPESCRIPT_NIDOCA_COMPONENT_EDIT,
    ITEM_TYPESCRIPT_NIDOCA_COMPONENT_LIST,
    ITEM_TYPESCRIPT_NIDOCA_COMPONENT_COMBOBOX,
}
