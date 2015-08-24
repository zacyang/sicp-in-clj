(ns sicp.ch4.core-test
  (:use [midje.sweet]
        [sicp.ch4.core]))

(fact "EVAL should return exp itself when it's string or number"
      (let [env {}]
        (EVAL "STRING" env) => "STRING"
        (EVAL 1 env) => 1))

(fact "definition tests"
      (definition? 1) => false
      (definition? '(define something)) => true
      (definition? '(define something 1)) => true

      "definition-value should be able to get the def value as un-eval list"
      (definition-value '(define (some-fn arg1 arg2) (body))) => '(lambda (arg1 arg2) (body))
      (definition-value '(define some-fn (lambda (arg1 arg2) (body)))) => '(lambda (arg1 arg2) (body))
      
      (definition-value '(define some-value (+ 1 1))) => '(+ 1 1)

      "defnition-variable should be able to get the variable name"
      (definition-variable '(define (some-fn arg1 arg2) (body))) => some-fn
      (definition-variable '(define some-fn (lambda (arg1 arg2) (body)))) => some-fn
      (definition-variable '(define some-value 1)) => 'some-value)

(fact "lambda tests"
      (lambda? '(lambda (arg1 arg2) (body))) => true
      (lambda? '(hehe (arg1 arg2) (body))) => false
      
      (lambda-parameters '(lambda (arg1 arg2) body)) => '(arg1 arg2)

      (lambda-body '(lambda (arg1 arg2) body)) => '(body))

(fact "cond tests"
      (cond?  '(cond)) => true
      (cond->clauses '(cond test1 do1 test2 do2)) => '(test1 do1 test2 do2)

      (expand-clauses (cond->clauses '(cond (test1 do1) (test2 do2))))
      =>
      '(if test1 (begin do1) (if test2 (begin do2) nil)))

(fact "env tests"
      @(make-frame '(a b c) '(1 2 3)) => '((a b c) 1 2 3)
      @(make-frame '() '() ) => '(())

      (let [frame (make-frame '(a b c) '(1 2 3))]
        (frame-variables frame) => '(a b c)
        (frame-values frame) => '(1 2 3)
        )

      (add-binding-to-frame! 'c 1 (make-frame '() '())) => '((c) 1)
      (add-binding-to-frame! 'c 1 (make-frame '(a) '(1))) => '((c a) 1 1)
      (add-binding-to-frame! 'c 1 (make-frame '(a b) '(3 4))) => '((c a b) 1 3 4)

      "extend env"
      @(extend-enviroment '(e) '(42) (make-frame '() '())) => '(((e) 42) ())

      "empty env = the-empty-env"
      (= @(make-frame '() '()) @the-empty-environment) => true

      "extend-enviroment"
      (let [extend-env (extend-enviroment '(e) '(42) (make-frame '() '()))]
        @(first-frame extend-env) => '((e) 42)
        (frame-variables (first-frame extend-env)) => '(e)
        (frame-values (first-frame extend-env)) => '(42)
        @(enclosing-enviroment extend-env) => '(())
        )
      
      @(extend-enviroment '(a) '(1) the-empty-environment) => '(((a) 1) ())
      (let [extend-env (extend-enviroment '(a) '(1) the-empty-environment)]
        @extend-env => '(((a) 1) ())
        @(first-frame extend-env) => '((a) 1)))

(fact "env lookup tests"
      (let [compound-env (extend-enviroment '(e) '(42) (make-frame '() '()))]
        (lookup-variable-value 'e compound-env) => 42
        (lookup-variable-value 'a compound-env) => :ERROR-NO-BOUND-VARIABLE
        )
     
      "should also be able to find val binding in the enclosing env, when binding not exists in current frame"
      (let [compound-env (extend-enviroment '(e) '(42) (extend-enviroment '(a) '(99) the-empty-environment))]
        (lookup-variable-value 'a compound-env) => 99)


      (let [compound-env (extend-enviroment '(e var1 var2 var3) '(42 "var1-val" "var2-val" "var3-val") (extend-enviroment '(a) '(99) the-empty-environment))]
        (lookup-variable-value 'var1 compound-env) => "var1-val")
      
)

(fact "change var binding , for current frame"
       (let [compound-env (extend-enviroment '(e) '(42) (extend-enviroment '(a) '(99) the-empty-environment))]
        "not exist var, error"
        @(set-variable-value! 'b 2 compound-env) => '(((e) 42) ((a) 99) ())
        
         
        "existing binding var, change enviroment"
        @(set-variable-value! 'a 1 compound-env) => '(((e) 42) ((a) 1) ())
        @(set-variable-value! 'e 1 compound-env) => '(((e) 1) ((a) 99) ())
        
        (set-variable-value! 'not-exist 22 the-empty-environment) => :ERROR-TRY-SET-UNBOUND-VARIABLE

        
        "define tests"
        @(define-variable! 'some-var '77 compound-env) => '(((some-var e) 77 42) ((a) 99) ())
)

(fact "def tests"
      (let  [compound-env (extend-enviroment '(e) '(42) (extend-enviroment '(a) '(99) the-empty-environment))]
      (eval-definition '(define x 10) compound-env) => :OK
      
      @compound-env = > '(((x e) 10 42) ((a) 99) ()))
      
      (let  [compound-env (extend-enviroment '(e) '(42) (extend-enviroment '(a) '(99) the-empty-environment))]
        (EVAL 'x compound-env) => :ERROR-NO-BOUND-VARIABLE
       
        (EVAL '(define x 10) compound-env)

        (EVAL 'x compound-env) => 10

        (EVAL '(define (some-function x) (+ 1 x)) compound-env) => :OK

        ))

)
      




