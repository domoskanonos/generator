import {EnumKeyValue, BasicEnumRepository} from "@domoskanonos/frontend-basis";
import {Property,Item,Project,Process,PropertyType,LanguageType,Template} from '../model/model'

export class PropertyTypeBasicEnumRepository extends BasicEnumRepository {

    private static uniqueInstance: PropertyTypeBasicEnumRepository;

    static getUniqueInstance() {
        if (!PropertyTypeBasicEnumRepository.uniqueInstance) {
            PropertyTypeBasicEnumRepository.uniqueInstance = new PropertyTypeBasicEnumRepository();
        }
        return PropertyTypeBasicEnumRepository.uniqueInstance;
    }

    public asI18nEnumKeyValueArray(): EnumKeyValue[] {
        return this.asEnumKeyValueArrayI18n(PropertyType);
    }

}