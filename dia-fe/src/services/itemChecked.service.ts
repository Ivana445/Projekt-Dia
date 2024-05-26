import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class ItemCheckedService {
    private checkedItems: Set<number> = new Set<number>();
    private totalItemCount: number = 0;
    addCheckedItem(itemId: number): void {
        this.checkedItems.add(itemId);
        this.totalItemCount++;
    }

    removeCheckedItem(itemId: number): void {
        this.checkedItems.delete(itemId);
        this.totalItemCount--;
    }

    isChecked(itemId: number): boolean {
        return this.checkedItems.has(itemId);
    }

    getCheckedItemCount(): number {
        return this.checkedItems.size;
    }

    getCheckedItems(): Set<number> {
        return this.checkedItems;
    }
    // Metóda pre získanie celkového počtu položiek
    getTotalItemCount(): number {
        return this.totalItemCount;
    }
}
