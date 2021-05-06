(defproject postie "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/test.check "1.1.0"]
                 [com.gfredericks/test.chuck "0.2.10"]
                 [org.clojure/spec.alpha "0.2.194"]
                 [metosin/malli "0.2.1"]]
  :repl-options {:init-ns postie.core})
