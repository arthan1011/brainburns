/**
 * Created by arthan on 05.03.2017.
*/

import {Component, ElementRef, AfterViewInit, ViewChild} from "@angular/core";
import {NotificationService} from "./notification.service";
import {Subscription} from "rxjs/Subscription";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/delay";
import "rxjs/add/operator/first";

@Component({
    selector: 'notification-popup',
    moduleId: module.id,
    templateUrl: 'html/notification.component.html',
    styleUrls: ["css/notification.component.css"]
})
export class NotificationPopupComponent implements AfterViewInit {
    @ViewChild('container')
    private container: ElementRef;
    private popups: Popup[] = [];

    private notificationSub: Subscription;

    constructor(
        private notificationService: NotificationService
    ) {
        this.notificationSub = notificationService.$notification
            .subscribe((message: string) => this.showNewMessage(message))
    }

    ngAfterViewInit() {
        console.log(this.container.nativeElement.innerHTML)
    }

    private showNewMessage(message: string) {
        this.popups.push(new Popup(message));

        let timer = Observable.of(1);
        timer.delay(6000).first().subscribe(() => {
            this.popups.shift();
        });
    }
}

class Popup {
    constructor(public text: string) {}
}