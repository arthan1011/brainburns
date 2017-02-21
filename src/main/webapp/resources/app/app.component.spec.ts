/**
 * Created by arthan on 18.02.2017.
*/
import {RouterTestingModule} from '@angular/router/testing';
import {APP_BASE_HREF} from '@angular/common';
import {ComponentFixture, TestBed} from "@angular/core/testing";
import {AppComponent} from "./app.component";
import {DebugElement} from "@angular/core";
import {By} from "@angular/platform-browser";
import { async } from '@angular/core/testing';

describe('AppComponent template', () => {
    let comp: AppComponent;
    let fixture: ComponentFixture<AppComponent>;
    let de: DebugElement;
    let el: HTMLElement;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [AppComponent],
            providers: [{provide: APP_BASE_HREF, useValue : '/' }],
            imports: [ RouterTestingModule ]
        }).compileComponents();
        console.log('Compiled!');
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(AppComponent);
        comp = fixture.componentInstance;

        de = fixture.debugElement.query(By.css('.main-header h1'));
        el = de.nativeElement;
    });

    it('Should display original title', () => {
        fixture.detectChanges();
        expect(el.textContent).toContain(comp.title);
    });
});