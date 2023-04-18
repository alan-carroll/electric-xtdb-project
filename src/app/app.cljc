(ns app.app
  (:require #?(:clj [app.xtdb-contrib :as db])
            [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui]
            [xtdb.api #?(:clj :as :cljs :as-alias) xt]))

(e/def !xtdb)
(e/def db) ; injected database ref; Electric defs are always dynamic

(e/defn App []
  (e/server
    (binding [!xtdb user/!xtdb
              db (new (db/latest-db> user/!xtdb))]
      (e/client
       (dom/link (dom/props {:rel :stylesheet :href "/styling.css"}))
       (dom/h1 (dom/text "Hello world!"))))))