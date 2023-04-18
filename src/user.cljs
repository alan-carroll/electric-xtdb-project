(ns ^:dev/always user
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [app.app :as app]))

(def electric-main
  (e/boot
   (binding [dom/node js/document.body]
     (app/App.))))

(defonce reactor nil)

(defn ^:dev/after-load ^:export start! []
  (assert (nil? reactor) "reactor already running")
  (set! reactor (electric-main
                 #(js/console.log "Reactor success:" %)
                 #(js/console.error "Reactor failure:" %))))

(defn ^:dev/before-load stop! []
  (when reactor (reactor)) ; teardown
  (set! reactor nil))
