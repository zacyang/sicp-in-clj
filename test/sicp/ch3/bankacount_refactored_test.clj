(ns sicp.ch3.bankacount_refactored
  (:use [midje.sweet]
        [sicp.ch3.bankacount_refactored]
        [sicp.ch3.bankacount :only [make-account]])
  )

(fact "3.3 account mgr with pwd"
      (let [fn-under-test (-> (make-account 100) (with-security "secret-password"))]
        (((fn-under-test "secret-password") :withdraw) 40)  => 60
        (((fn-under-test "invalid-password") :withdraw) 60)  => "Incorrect password"
        
))

(fact "3.4 account access try limit"
      (let [fn-under-test (-> (make-account 100) (with-try-limit 3))]
        ((fn-under-test  :withdraw) 10) =>  90
        ((fn-under-test  :withdraw) 10) =>  80
        ((fn-under-test  :withdraw) 10) =>  70
        ((fn-under-test  :withdraw) 10) => "Limit exceeded, policy is informed!"
))

(fact "all in one"
      (let [fn-under-test (-> (make-account 100) (with-try-limit 3) (with-security "pwd"))]
        (((fn-under-test "pwd") :withdraw ) 10) => 90

      (((fn-under-test "invalid-pwd") :withdraw ) 10) => "Incorrect password"
      (((fn-under-test "123-pwd") :withdraw ) 10) => "Incorrect password"
      (((fn-under-test "hehe-pwd") :withdraw ) 10) => "Incorrect password"

      (((fn-under-test "hehe-pwd") :withdraw ) 10) => "Limit exceeded, policy is informed!"

))
