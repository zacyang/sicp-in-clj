(ns sicp.ch3.bankacount
  (:use [midje.sweet]
        [sicp.ch3.bankacount])
  (:import java.lang.Math))

(fact "3.1 accumulator impl"
      (let [accumulator (make-accumulator 5)]
       (accumulator 10) =>  15
       (accumulator 10) => 25)
      )

(fact "3.2 invoke inner counter"
      (let [fn-under-test (make-monitored square)]
        (s 100) => 10
        (s :how-many-calls?) => 1))
