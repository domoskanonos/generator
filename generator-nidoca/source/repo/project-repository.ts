import {HttpClientService, BasicRemoteRepository} from "@domoskanonos/frontend-basis";
import {Project} from "../model/project-model";

export class ProjectRemoteRepository extends BasicRemoteRepository<Project, number> {

    private static uniqueInstance: ProjectRemoteRepository;

    constructor() {
        super(HttpClientService.getUniqueInstance(),"/PROJECT");
    }

    static getUniqueInstance() {
        if (!ProjectRemoteRepository.uniqueInstance) {
            ProjectRemoteRepository.uniqueInstance = new ProjectRemoteRepository();
        }
        return ProjectRemoteRepository.uniqueInstance;
    }

}