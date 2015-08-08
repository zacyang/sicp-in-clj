(ns sicp.ch4.setup_test
  (:use [sicp.ch4.setup]
        [midje.sweet])
  (:require [sicp.ch4.core :as core]))

(fact "stepup tests"

      
      (core/EVAL 'x (core/EVAL '(define x 10) the-global-environment) ) => '()
)
