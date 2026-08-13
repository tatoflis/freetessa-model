/* tslint:disable */
/* eslint-disable */

export class AbstractEntityRequest {
    id: number;
    version: number;
    name: string;
    description: string;
    icon: string;
    modifDate: Date;
}

export class AssetRequest extends AbstractEntityRequest {
    identificator: string;
    idTemplate: number;
    idAssetBase: number;
    active: boolean;
    insertDate: Date;
}

export class AbstractIdentificableEntityResponse {
    id: number;
}

export class AbstractEntityResponse extends AbstractIdentificableEntityResponse {
    version: number;
    name: string;
    description: string;
    icon: string;
    insertDate: Date;
    modifDate: Date;
    nemonic: string;
    groups: string[];
}

export class AssetResponse extends AbstractEntityResponse {
    identificator: string;
    active: boolean;
    physicalPath: string;
    logicalPath: string[];
    template: TemplateResponse;
    assetDependsResponse: AssetResponse;
    numComplexAssets: number;
    values: AssetValueResponse[];
    organizers: OrganizerResponse[];
}

export class AssetReferenceRequest {
    id: number;
    idAsset: number;
    referenceType: string;
    relationType: string;
}

export class AssetReferenceResponse extends AbstractIdentificableEntityResponse {
    assetResponse: AssetResponse;
    referenceType: string;
    relationType: string;
}

export class AssetValueRequest extends AbstractEntityRequest {
    value: string;
    expressionProperties: FunctionRequest;
    idAsset: number;
    idAssetReference: number;
    idTemplateAttribute: number;
    idTemplateAttributeCollectionMapping: number;
    idBinary: string;
    expressionParams: ExpressionParamRequest[];
}

export class AssetValueResponse extends AbstractEntityResponse {
    assetReference: AssetReferenceResponse;
    templateAttribute: TemplateAttributeResponse;
    asset: AssetResponse;
    templateAttributeCollectionMapping: TemplateAttributeCollectionMappingResponse;
    value: string;
    idBinary: string;
    expressionProperties: FunctionResponse;
    expressionParams: ExpressionParamResponse[];
}

export class HashtagRequest extends AbstractEntityRequest {
    hashtag: string;
}

export class HashtagResponse extends AbstractEntityResponse {
    hashtag: string;
}

export class OrganizerRequest extends AbstractEntityRequest {
    type: string;
    idAssetMetadata: number;
    idParentOrganizer: number;
}

export class OrganizerResponse extends AbstractEntityResponse {
    type: string;
    metadata: AssetResponse;
    parentOrganizer: OrganizerResponse;
    numElements: number;
    numOrganizers: number;
    organizerPath: string[];
    /** @deprecated Use organizerPath[0] instead */
    physicalPath: string;
    path: string[];
}

export class DefaultValueAssetValueRequest extends AbstractEntityRequest {
    order: number;
    value: string;
}

export class DefaultValueAssetValueResponse extends AbstractEntityResponse {
    order: number;
    value: string;
}

export class ExpressionParamRequest extends AbstractEntityRequest {
    type: string;
    required: boolean;
    position: number;
    idTemplateAttribute: number;
    defaultValueAssetValueRequest: DefaultValueAssetValueRequest;
}

export class ExpressionParamResponse extends AbstractEntityResponse {
    type: string;
    required: boolean;
    position: number;
    templateAttributeResponse: TemplateAttributeResponse;
    defaultValueAssetValueResponse: DefaultValueAssetValueResponse;
}

export class TemplateAttributeCollectionMappingRequest extends AbstractEntityRequest {
    calculatedValue: string;
    position: number;
}

export class TemplateAttributeCollectionMappingResponse extends AbstractEntityResponse {
    calculatedValue: string;
    templateAttribute: TemplateAttributeResponse2;
    position: number;
}

export class TemplateAttributeRequest extends AbstractEntityRequest {
    type: string;
    minLength: number;
    maxLength: number;
    required: boolean;
    hidden: boolean;
    hasDefaultValue: boolean;
    defaultValue: string;
    hasCalculatedValue: boolean;
    calculatedValue: string;
    expressionProperties: FunctionRequest;
    pattern: string;
    unique: boolean;
    externalSource: boolean;
    collection: boolean;
    withcapacity: boolean;
    capacity: number;
    mapping: boolean;
    finalAttribute: boolean;
    password: boolean;
    identificator: boolean;
    alias: boolean;
    idTemplateReference: number;
    idTemplate: number;
    position: number;
    relationType: string;
    referenceType: string;
    contentType: string;
    enums: string[];
    expressionParams: ExpressionParamRequest[];
}

export class TemplateAttributeResponse extends AbstractEntityResponse {
    type: string;
    minLength: number;
    maxLength: number;
    required: boolean;
    hidden: boolean;
    hasDefaultValue: boolean;
    defaultValue: string;
    hasCalculatedValue: boolean;
    calculatedValue: string;
    expressionProperties: FunctionResponse;
    pattern: string;
    unique: boolean;
    externalSource: boolean;
    collection: boolean;
    enums: string[];
    withcapacity: boolean;
    capacity: number;
    mapping: boolean;
    finalAttribute: boolean;
    password: boolean;
    identificator: boolean;
    alias: boolean;
    template: TemplateResponse;
    templateReference: TemplateReferenceResponse;
    expressionParams: ExpressionParamResponse[];
    position: number;
    contentType: string;
}

export class TemplateRequest extends AbstractEntityRequest {
    assetOrganized: boolean;
    templateOrganized: boolean;
    type: string;
    idExtendsTemplate: number;
    final: boolean;
    abstract: boolean;
}

export class TemplateResponse extends AbstractEntityResponse {
    assetOrganized: boolean;
    templateOrganized: boolean;
    type: string;
    extendsTemplate: TemplateResponse;
    templateAttributes: TemplateAttributeResponse[];
    numComplexAttributes: number;
    final: boolean;
    abstract: boolean;
    templatePath: string[];
    /** @deprecated Use templatePath[0] instead */
    physicalPath: string;
    path: string[];
}

export class Filter {
    propertyFilters: PropertyFilter[];
    count: boolean;
}

export class AssetFilter extends Filter {
    organizerFilter: PropertyFilter[];
    templateFilter: PropertyFilter[];
    assetValueFilter: PropertyFilter[];
    assetReferenceFilter: PropertyFilter[];
    assetValueReferencedFilter: PropertyFilter[];
    templateAttributeFilter: PropertyFilter[];
    hashtagFilter: PropertyFilter[];
    assetValueTemplateAttributeFilter: AssetValueTemplateAttributeFilter[];
    referenceFilter: AssetReferenceFilter[];
    idsAssets: number[];
    countChild: boolean;
    assetType: string;
    withPath: boolean;
    system: boolean;
    hierarchyType: string;
    referenced: boolean;
    getDependFromAsset: boolean;
    idAssetParent: number;
    idsAssetParent: number[];
    idsAssetChilds: number[];
    idSelectedAsset: number;
    idsSelectedAsset: number[];
    idSelectedOrganizer: number;
    idsSelectedOrganizer: number[];
}

export class AssetValueFilter extends Filter {
    assetFilter: PropertyFilter[];
    assetReferenceFilter: PropertyFilter[];
    templateReferenceFilter: PropertyFilter[];
    templateAssetReferenceFilter: PropertyFilter[];
    templateAttributeFilter: PropertyFilter[];
    templateFilter: PropertyFilter[];
    idsAssets: number[];
    idsAssetReference: number[];
    relationshipFilter: PropertyFilter[];
}

export class HashtagFilter extends Filter {
    assetFilter: PropertyFilter[];
    templateFilter: PropertyFilter[];
    templateAttributeFilter: PropertyFilter[];
}

export class OrganizerFilter extends Filter {
    parentOrganizerFilter: PropertyFilter[];
    templateFilter: PropertyFilter[];
    assetFilter: PropertyFilter[];
    metadataFilter: PropertyFilter[];
}

export class TemplateAttributeCollectionMappingFilter extends Filter {
    templateAttributeFilter: PropertyFilter[];
}

export class TemplateAttributeFilter extends Filter {
    hashtagFilter: PropertyFilter[];
    templateFilter: PropertyFilter[];
    /**
     * @deprecated for removal
     */
    templateAttributeExtendsFilter: PropertyFilter[];
    templateReferenceFilter: PropertyFilter[];
    hierarchyType: string;
}

export class TemplateFilter extends Filter {
    organizerFilter: PropertyFilter[];
    templateAttributeFilter: PropertyFilter[];
    templateReferenceFilter: PropertyFilter[];
    hashtagFilter: PropertyFilter[];
    referenceFilter: TemplateReferenceFilter[];
    idTemplateParent: number;
    countChild: boolean;
    withPath: boolean;
    fullTemplate: boolean;
    fullHierarchy: boolean;
    hierarchyType: string;
    templateReferenceType: string;
}

export class PropertyFilter {
    propertyName: string;
    propertyValue: any;
    logicalOperatorType: LogicalOperatorType;
    attributesOrder: AttributeOrder[];
    arithmeticOperatorType: ArithmeticOperatorType;
}

export class AttributeOrder {
    attributeName: string;
    orderType: OrderType;
}

export class FunctionRequest {
    expressionFunction: string;
    refillingCalculation: boolean;
    ignoreNoData: boolean;
    cronExpression: string;
    expressionEvent: string;
    cronTimeZone: string;
    cronDelay: number;
}

export class FunctionResponse {
    expressionFunction: string;
    refillingCalculation: boolean;
    ignoreNoData: boolean;
    cronExpression: string;
    expressionEvent: string;
    cronTimeZone: string;
    cronDelay: number;
}

export class TemplateAttributeResponse2 extends AbstractEntityResponse {
    type: string;
    minLength: number;
    maxLength: number;
    required: boolean;
    hasDefaultValue: boolean;
    defaultValue: string;
    cronExpression: string;
    cronTimeZone: string;
    cronDelay: number;
    hasCalculatedValue: boolean;
    calculatedValue: string;
    expressionFunction: string;
    refillingCalculation: boolean;
    ignoreNoData: boolean;
    pattern: string;
    unique: boolean;
    externalSource: boolean;
    collection: boolean;
    withcapacity: boolean;
    capacity: number;
    mapping: boolean;
    finalAttribute: boolean;
    password: boolean;
    identificator: boolean;
    templateReference: TemplateReferenceResponse;
    template: TemplateResponse;
    position: number;
    contentType: string;
}

export class TemplateReferenceResponse extends AbstractIdentificableEntityResponse {
    referenceType: ReferenceType;
    relationType: RelationType;
    templateResponse: TemplateResponse;
}

export class AssetValueTemplateAttributeFilter {
    assetValuePropertyFilter: PropertyFilter;
    templateAttributePropertyFilter: PropertyFilter;
    assetValuePropertyFilters: PropertyFilter[];
}

export class AssetReferenceFilter {
    assetFilter: PropertyFilter[];
    organizerFilter: PropertyFilter[];
    templateFilter: PropertyFilter[];
    assetValueFilter: PropertyFilter[];
    templateAttributeFilter: PropertyFilter[];
    assetValueTemplateAttributeFilter: AssetValueTemplateAttributeFilter[];
    hashtagFilter: PropertyFilter[];
    assetValueReferenceToFilter: AssetValueTemplateAttributeFilter;
    relationshipFilter: PropertyFilter[];
    relationshipDirection: string;
    referenceFilter: AssetReferenceFilter[];
}

export class TemplateReferenceFilter {
    templateFilter: PropertyFilter[];
    organizerFilter: PropertyFilter[];
    templateAttributeFilter: PropertyFilter[];
    hashtagFilter: PropertyFilter[];
    relationshipFilter: PropertyFilter[];
    relationshipDirection: string;
    hierarchyType: string;
}

export enum OrganizerType {
    ASSET_ORGANIZER = "Asset Organizer",
    TEMPLATE_ORGANIZER = "Template Organizer",
}

export enum AssetType {
    SYNTETIC_ASSET = "SynteticAsset",
    ASSET = "Asset",
    ASSET_BASE = "AssetBase",
    ASSET_AND_ASSET_BASE = "AssetAndAssetBase",
    SYNTETIC_ASSET_AND_ASSET = "SynteticAssetAndAsset",
}

export enum LookupType {
    LOOKUP_DB = "LookupDB",
    LOOKUP_REST = "LookupRest",
    LOOKUP_PROMETHEUS_REST = "LookupPrometheusRest",
    LOOKUP_WEBSERVICE = "LookupWebservice",
}

export enum ReferenceType {
    COMPLEX = "Complex",
    SIMPLE = "Simple",
}

export enum RelationType {
    ASSOCIATION = "Association",
    AGGREGATION = "Aggregation",
    COMPOSITION = "Composition",
}

export enum TemplateAttributeType {
    STRING = "String",
    LONGTEXT = "Longtext",
    INTEGER = "Integer",
    DECIMAL = "Decimal",
    DATE = "Date",
    DATETIME = "Datetime",
    BOOLEAN = "Boolean",
    BINARY = "Binary",
    COMPLEX = "Complex",
    COMPLEX_DEPENDS = "ComplexDepends",
    SIMPLE = "Simple",
    FUNCTION = "Function",
}

export enum TemplateType {
    COMPLEX = "Complex",
    SIMPLE = "Simple",
    LOOKUP = "Lookup",
    METADATA = "Metadata",
}

export enum ContentType {
    PDF = "PDF",
    PNG = "PNG",
}

export enum LogicalOperatorType {
    AND = "AND",
    OR = "OR",
}

export enum ArithmeticOperatorType {
    EQUALS = "EQUALS",
    CONTAINS = "CONTAINS",
    NOT_CONTAINS = "NOT_CONTAINS",
    STARTS_WITH = "STARTS_WITH",
    ENDS_WITH = "ENDS_WITH",
    MINOR = "MINOR",
    MAYOR = "MAYOR",
    MINOR_EQUALS = "MINOR_EQUALS",
    MAYOR_EQUALS = "MAYOR_EQUALS",
    NOT = "NOT",
    NOT_EXISTS = "NOT_EXISTS",
    EXISTS = "EXISTS",
}

export enum OrderType {
    ASC = "ASC",
    DESC = "DESC",
}
