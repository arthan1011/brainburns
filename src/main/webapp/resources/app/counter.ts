import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { Observable } from 'rxjs/Observable';

export class Counter {
	private _count = 0;
	private _value = new BehaviorSubject<number>(this._count);

	increment() {
		this._value.next(++this._count);
	}

	decrement() {
		this._value.next(--this._count);
	}

	get value() {
		return this._value.asObservable();
	}
}