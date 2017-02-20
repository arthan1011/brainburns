Error.stackTraceLimit = 0;
jasmine.DEFAULT_TIMEOUT_INTERVAL = 1000;

// (1)
var builtPaths = (__karma__.config.builtPaths || ['app/'])
                  .map(function(p) { return '/base/' + p; });

// (2)
__karma__.loaded = function () { };

// (3)
function isJsFile(path) {
  return path.slice(-3) == '.js';
}

// (4)
function isSpecFile(path) {
  return /\.spec\.(.*\.)?js$/.test(path);
}

// (5)
function isBuiltFile(path) {
  return isJsFile(path) &&
          builtPaths.reduce(function(keep, bp) {
            return keep || (path.substr(0, bp.length) === bp);
          }, false);
}

// (6)
var allSpecFiles = Object.keys(window.__karma__.files)
  .filter(isSpecFile)
  .filter(isBuiltFile);

// (7)
SystemJS.config({
  baseURL: 'base'
});

// (8)
System.import('systemjs.config.js')
  .then(initTesting);

// (9)
function initTesting () {
  return Promise.all(
    allSpecFiles.map(function (moduleName) {
      return System.import(moduleName);
    })
  )
  .then(__karma__.start, __karma__.error);
}

