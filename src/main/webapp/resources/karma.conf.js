/**
 * Created by arthan on 15.02.2017. | Project brainburns
*/

module.exports = function (config) {
    config.set({
        basePath: '',
        frameworks: ['jasmine'],

        browsers: ['Chrome'],

        files: [
            {pattern: 'systemjs.config.js', included: false, watcher: false},

            'node_modules/systemjs/dist/system.src.js',
            'karma-test-shim.js',

            'node_modules/core-js/client/shim.min.js',
            'node_modules/reflect-metadata/Reflect.js',
            'node_modules/zone.js/dist/zone.js',

            {pattern: 'app/**/*.js', included: false, watcher: false},
            {pattern: 'app/**/*.js.map', included: false, watcher: false},
            {pattern: 'app/**/*.html', included: false, watcher: false},
            {pattern: 'app/**/*.css', included: false, watcher: false},
            {pattern: 'node_modules/@angular/**/*.js', included: false, watched: false},
            {pattern: 'node_modules/@angular/**/*.js.map', included: false, watched: false},
            {pattern: 'node_modules/rxjs/**/*.js', included: false, watched: false},
            {pattern: 'node_modules/rxjs/**/*.js.map', included: false, watched: false},

            'app/first-test.spec.js',
            { pattern: 'app/counter/counter.js', included: false, watched: true },
            // 'app/app.component.spec.js'
        ],

        // Karma plugins loaded
        plugins: [
            'karma-jasmine',
            'karma-chrome-launcher'
        ],

        logLevel: config.LOG_DEBUG,

        singleRun: false
    });
};