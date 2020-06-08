export class ItemRemoteService {

    private static uniqueInstance: ItemRemoteService;

    constructor() {
    }

    static getUniqueInstance() {
        if (!ItemRemoteService.uniqueInstance) {
            ItemRemoteService.uniqueInstance = new ItemRemoteService();
        }
        return ItemRemoteService.uniqueInstance;
    }

}