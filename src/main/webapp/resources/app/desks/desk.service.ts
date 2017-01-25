/**
 * Created by arthan on 18.01.2017. | Project brainburns
 */
import {Injectable} from "@angular/core";
import {Desk} from "./model/Desk";
import {Http, Response} from "@angular/http";
import "rxjs/add/operator/toPromise";
import "rxjs/add/operator/do";
import "rxjs/add/operator/map";
import "rxjs/add/operator/find";
import "rxjs/add/observable/of";
import {Observable} from "rxjs/Observable";
import {Subject} from "rxjs/Subject";

const URL_DESKS = "/api/desk";

@Injectable()
export class DeskService {

    constructor(private http: Http) {}

    private deskCreatedSource = new Subject();
    private deskListUpdateSource = new Subject();
    deskCreated$: Observable<any> = this.deskCreatedSource.asObservable();
    deskListUpdate$: Observable<any> = this.deskListUpdateSource.asObservable();

    desks: Desk[];

    getDesks(): Observable<Desk[]> {
        if (this.desks) {
            return Observable.of(this.desks);
        }
        return this.http.get(URL_DESKS)
            .map((res: Response) => res.json().data)
            .do(desks => this.desks = desks);
    }

    getDesk(id: number): Observable<Desk> {
        return this.getDesks()
            .map(desks => desks.find(desk => desk.id === id));
    }

    createDesk(desk: Desk) {
        this.http.post(URL_DESKS, desk)
            .do(this.desks = null)
            .do(() => this.deskCreatedSource.next("success"))
            .subscribe();
    }

    updateDesks() {
        this.deskListUpdateSource.next(true);
    }
}