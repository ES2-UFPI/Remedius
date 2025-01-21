export interface StockObserver {
    update(medication: string, quantity: number): void
  }
  
  export class StockNotifier {
    private observers: StockObserver[] = []
  
    addObserver(observer: StockObserver) {
      this.observers.push(observer)
    }
  
    removeObserver(observer: StockObserver) {
      const index = this.observers.indexOf(observer)
      if (index > -1) {
        this.observers.splice(index, 1)
      }
    }
  
    notifyObservers(medication: string, quantity: number) {
      for (const observer of this.observers) {
        observer.update(medication, quantity)
      }
    }
  }
  