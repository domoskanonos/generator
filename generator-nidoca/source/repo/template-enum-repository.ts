import {EnumKeyValue, BasicEnumRepository} from "@domoskanonos/frontend-basis";
import {Property,Item,Project,Process,PropertyType,LanguageType,Template} from '../model/model'

export class TemplateBasicEnumRepository extends BasicEnumRepository {

    private static uniqueInstance: TemplateBasicEnumRepository;

    static getUniqueInstance() {
        if (!TemplateBasicEnumRepository.uniqueInstance) {
            TemplateBasicEnumRepository.uniqueInstance = new TemplateBasicEnumRepository();
        }
        return TemplateBasicEnumRepository.uniqueInstance;
    }

    public asI18nEnumKeyValueArray(): EnumKeyValue[] {
        return this.asEnumKeyValueArrayI18n(Template);
    }

}