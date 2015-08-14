(defproject sicp-in-clj "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main sicp.ch4.repl
  :uberjar-name "clj-scheme.jar"
  :dependencies [[org.clojure/clojure "1.6.0"]]
  
  :plugins [[cider/cider-nrepl "0.9.1"]]
  :profiles {:dev 
             {:dependencies [[midje "1.7.0"]]}}
  )
