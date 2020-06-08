export class PropertyRemoteService {

    private static uniqueInstance: PropertyRemoteService;

    constructor() {
    }

    static getUniqueInstance() {
        if (!PropertyRemoteService.uniqueInstance) {
            PropertyRemoteService.uniqueInstance = new PropertyRemoteService();
        }
        return PropertyRemoteService.uniqueInstance;
    }

}