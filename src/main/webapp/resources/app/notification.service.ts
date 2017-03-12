/**
 * Created by arthan on 05.03.2017.
*/

import {Injectable} from "@angular/core";
import {Subject} from "rxjs/Subject";

@Injectable()
export class NotificationService {

    private notificationSource = new Subject();
    public $notification = this.notificationSource.asObservable();

    public error(message: string) {
        console.log(`Notification: ${message}`);
        this.notificationSource.next(message);
    }
}