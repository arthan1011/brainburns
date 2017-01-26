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

const URL_DESKS = "/api/desk";

@Injectable()
export class DeskService {
    desks: Desk[];

    constructor(private http: Http) {}

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
        return this.http.post(URL_DESKS, desk)
            .do(this.desks = null)

    }
}