import { Counter } from './counter';

describe('Counter', () => {
	let counter: Counter;

	beforeEach(() => {
		counter = new Counter();
	});

	it('should increase value of counter when incremented', (done) => {
		counter.increment();
		counter.value.subscribe(value => {
			expect(value).toEqual(1);
			done();
		});
	});

	it('should decrease value of counter when decremented', (done) => {
    counter.decrement();
		counter.value.subscribe(value => {
			expect(value).toEqual(-1);
			done();
		});
  });
});