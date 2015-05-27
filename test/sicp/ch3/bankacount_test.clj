(ns sicp.ch3.bankacount
  (:use [midje.sweet]
        [sicp.ch3.bankacount])
  )

(fact "3.1 accumulator impl"
      (let [accumulator (make-accumulator 5)]
       (accumulator 10) =>  15
       (accumulator 10) => 25)
      )

(fact "3.2 invoke inner counter"
      (let [fn-under-test (make-monitored square)]
        (fn-under-test 10) => 100        
        (fn-under-test 10) => 100
        (fn-under-test :how-many-calls?) => 2))

(fact "book code testing"
      (let [fn-under-test (make-withdraw 100)]
        (fact (fn-under-test 90) => 10)
        (fact (fn-under-test 110) => "Insufficient funds")
        ))

(fact "account testing"
      (let [fn-under-test (make-account 100)]
        ((fn-under-test :withdraw) 10) => 90
        ))

(fact "3.3 account mgr with pwd"
      (let [fn-under-test (make-account-with-security "secret-password" 100 )]
        ((fn-under-test "secret-password" :withdraw) 40)  => 60
        ((fn-under-test "invalid-password" :withdraw) 60)  => "Incorrect password"
        
))

(fact "3.4 account access try limit"
      (let [fn-under-test (make-account-with-try-limit 3 100)]
        (fn-under-test :withdraw 10) => 90
        (fn-under-test :withdraw 10) => 80
        (fn-under-test :withdraw 10) => 70
        (fn-under-test :withdraw 10) => "Limit exceeded, policy is informed!"
))
