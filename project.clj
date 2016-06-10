(defproject update-repo-clj "0.1.0"
  :description "Updates repo from github hooks"
  :url "http://quilesbaker.com"
  :main "update-repo-clj.core"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [http-kit "2.1.18"]
                 [compojure "1.3.4"]
                 [org.clojure/tools.logging "0.3.1"]
                 [selmer "1.0.4"]]
  :profiles {
              :install {:aot :all
                        :omit-source true}
              :uberjar {:aot :all
                        :omit-source true}})
