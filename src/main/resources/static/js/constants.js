angular.module('constants', [])
  .constant('constants', {
    BOARD: {
      CELL: {
        EMPTY: ' ',
        X: 'x',
        O: 'o'
      }
    },
    STATES: {
      IN_PROGRESS: 'IN_PROGRESS'
    }
  });