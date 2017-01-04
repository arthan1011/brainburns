import {NgModule}      from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule}   from '@angular/forms';

import {AppComponent}  from './app.component';
import {CardFormComponent} from "./card-form.component";
import {DashboardComponent} from "./dashboard.component";
import {RouterModule} from "@angular/router";

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        RouterModule.forRoot([
            {
                path: '',
                redirectTo: 'dashboard',
                pathMatch: 'full'
            },
            {
                path: 'dashboard',
                component: DashboardComponent
            },
            {
                path: 'card/new',
                component: CardFormComponent
            }
        ])
    ],
    declarations: [
        AppComponent,
        CardFormComponent,
        DashboardComponent
    ],
    bootstrap: [
        AppComponent
    ]
})
export class AppModule {
}
