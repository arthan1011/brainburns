/**
 * Created by arthan on 15.02.2017. | Project brainburns
*/

module.exports = function (config) {
    config.set({
        basePath: '',
        frameworks: ['systemjs', 'jasmine'],

        browsers: ['Chrome'],

        files: [
            'node_modules/core-js/client/shim.min.js',
            'node_modules/reflect-metadata/Reflect.js',
            'node_modules/zone.js/dist/zone.js',
            'node_modules/systemjs/dist/system.src.js',

            {pattern: 'systemjs.config.js', included: false, watcher: false},

            'karma-test-shim.js',

            {pattern: 'app/**/*.js', included: false, watcher: false},
            {pattern: 'app/**/*.js.map', included: false, watcher: false},
            {pattern: 'app/**/*.html', included: false, watcher: false},
            {pattern: 'app/**/*.css', included: false, watcher: false},
            { pattern: 'node_modules/@angular/**/*.js', included: false, watched: false },
            { pattern: 'node_modules/@angular/**/*.js.map', included: false, watched: false },

            'app/first-test.spec.js',
            'app/app.component.spec.js'
        ],

        // Karma plugins loaded
        plugins: [
            'karma-jasmine',
            'karma-systemjs',
            'karma-chrome-launcher'
        ],

        logLevel: config.LOG_DEBUG,

        singleRun: false
    });
};