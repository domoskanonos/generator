import {HttpClientService, BasicRemoteRepository} from "@domoskanonos/frontend-basis";
import {PropertyModel} from "../model/property-model";

export class PropertyRemoteRepository extends BasicRemoteRepository<PropertyModel, number> {

    private static uniqueInstance: PropertyRemoteRepository;

    constructor() {
        super(HttpClientService.getUniqueInstance(),"PROPERTY");
    }

    static getUniqueInstance() {
        if (!PropertyRemoteRepository.uniqueInstance) {
            PropertyRemoteRepository.uniqueInstance = new PropertyRemoteRepository();
        }
        return PropertyRemoteRepository.uniqueInstance;
    }

}