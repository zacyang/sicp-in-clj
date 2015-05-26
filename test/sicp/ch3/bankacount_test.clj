(ns sicp.ch3.bankacount
  (:use [midje.sweet]
        [sicp.ch3.bankacount]))

(fact "3.1 accumulator impl"
      (let [accumulator (make-accumulator 5)]
       (accumulator 10) =>  15
       (accumulator 10) => 25)
      )
