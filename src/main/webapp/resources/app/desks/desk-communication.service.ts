/**
 * Created by arthan on 18.01.2017. | Project brainburns
 */
import {Injectable} from "@angular/core";
import {Desk} from "./model/Desk";
import "rxjs/add/operator/toPromise";
import "rxjs/add/operator/do";
import "rxjs/add/operator/map";
import "rxjs/add/operator/find";
import "rxjs/add/observable/of";
import {Observable} from "rxjs/Observable";
import {Subject} from "rxjs/Subject";

@Injectable()
export class DeskCommunicationService {

    constructor() {}

    private deskCreatedSource = new Subject();
    private deskListUpdateSource = new Subject();
    deskCreated$: Observable<any> = this.deskCreatedSource.asObservable();
    deskListUpdate$: Observable<any> = this.deskListUpdateSource.asObservable();

    desks: Desk[];

    deskCreated(message: any) {
        this.deskCreatedSource.next(message);
    }

    updateDesks() {
        this.deskListUpdateSource.next(true);
    }
}