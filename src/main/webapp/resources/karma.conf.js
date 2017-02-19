/**
 * Created by arthan on 15.02.2017. | Project brainburns
*/

module.exports = function (config) {
    config.set({
        basePath: '',
        frameworks: ['jasmine'],

        browsers: ['Chrome'],

        files: [
            'node_modules/systemjs/dist/system.src.js',
            {pattern: 'systemjs-test.config.js', included: false, watcher: false},
            'karma-test-shim.js',

            // Polyfills
            'node_modules/core-js/client/shim.js',
            'node_modules/reflect-metadata/Reflect.js',

            // zone.js
            'node_modules/zone.js/dist/zone.js',
            'node_modules/zone.js/dist/long-stack-trace-zone.js',
            'node_modules/zone.js/dist/proxy.js',
            'node_modules/zone.js/dist/sync-test.js',
            'node_modules/zone.js/dist/jasmine-patch.js',
            'node_modules/zone.js/dist/async-test.js',
            'node_modules/zone.js/dist/fake-async-test.js',

            {pattern: 'node_modules/@angular/**/*.js', included: false, watched: false},
            {pattern: 'node_modules/@angular/**/*.js.map', included: false, watched: false},

            {pattern: 'node_modules/rxjs/**/*.js', included: false, watched: false},
            {pattern: 'node_modules/rxjs/**/*.js.map', included: false, watched: false},

            { pattern: 'app/**/*.js', included: false, watched: true },
            { pattern: 'app/**/*.ts', included: false, watched: true },
            { pattern: 'app/**/*.js.map', included: false, watched: true },
            { pattern: 'app/**/*.html', included: false, watched: true },
            { pattern: 'app/**/*.css', included: false, watched: true }
        ],

        // Karma plugins loaded
        plugins: [
            'karma-jasmine',
            'karma-chrome-launcher',
            'karma-jasmine-html-reporter'
        ],

        reporters: ['progress', 'kjhtml'],

        logLevel: config.LOG_DEBUG,

        singleRun: false
    });
};