(defproject update-repo-clj "0.1.0"
  :description "Updates repo from github hooks"
  :url "http://quilesbaker.com"
  :main ^:skip-aot update-repo-clj.core
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [http-kit "2.1.18"]
                 [compojure "1.3.4"]
                 [org.clojure/tools.logging "0.3.1"]
                 [selmer "1.0.4"]
                 [com.taoensso/timbre "4.4.0"]
                 [org.xerial/sqlite-jdbc "3.7.2"]
                 [korma "0.4.0"]]
  :profiles {
              :install {:aot :all
                        :omit-source true}
              :uberjar {:aot :all
                        :omit-source true}})
