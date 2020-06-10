import {BasicEnumRepository} from "@domoskanonos/frontend-basis";

export class PropertyTypeBasicEnumRepository extends BasicEnumRepository {

    private static uniqueInstance: PropertyTypeBasicEnumRepository;

    static getUniqueInstance() {
        if (!PropertyTypeBasicEnumRepository.uniqueInstance) {
            PropertyTypeBasicEnumRepository.uniqueInstance = new PropertyTypeBasicEnumRepository();
        }
        return PropertyTypeBasicEnumRepository.uniqueInstance;
    }

}