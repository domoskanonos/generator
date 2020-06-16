import {EnumKeyValue, BasicEnumRepository} from "@domoskanonos/frontend-basis";
import {Property,Item,Project,Process,PropertyType,LanguageType,Template} from '../model/model'

export class LanguageTypeBasicEnumRepository extends BasicEnumRepository {

    private static uniqueInstance: LanguageTypeBasicEnumRepository;

    static getUniqueInstance() {
        if (!LanguageTypeBasicEnumRepository.uniqueInstance) {
            LanguageTypeBasicEnumRepository.uniqueInstance = new LanguageTypeBasicEnumRepository();
        }
        return LanguageTypeBasicEnumRepository.uniqueInstance;
    }

    public asI18nEnumKeyValueArray(): EnumKeyValue[] {
        return this.asEnumKeyValueArrayI18n(LanguageType);
    }

}