(ns sicp.ch4.core-test
  (:use [midje.sweet]
        [sicp.ch4.core]))

(fact "EVAL should return exp itself when it's string or number"
      (let [env {}]
        (EVAL "STRING" env) => "STRING"
        (EVAL 1 env) => 1))
