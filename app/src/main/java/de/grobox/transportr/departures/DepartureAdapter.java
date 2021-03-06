/*
 *    Transportr
 *
 *    Copyright (c) 2013 - 2018 Torsten Grote
 *
 *    This program is Free Software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as
 *    published by the Free Software Foundation, either version 3 of the
 *    License, or (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.grobox.transportr.departures;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collection;
import java.util.Date;

import de.grobox.transportr.R;
import de.schildbach.pte.dto.Departure;

class DepartureAdapter extends RecyclerView.Adapter<DepartureViewHolder> {

	private final SortedList<Departure> items = new SortedList<>(Departure.class, new SortedList.Callback<Departure>() {
		@Override
		public void onInserted(int position, int count) {
			notifyItemRangeInserted(position, count);
		}

		@Override
		public void onChanged(int position, int count) {
			notifyItemRangeChanged(position, count);
		}

		@Override
		public void onMoved(int fromPosition, int toPosition) {
			notifyItemMoved(fromPosition, toPosition);
		}

		@Override
		public void onRemoved(int position, int count) {
			notifyItemRangeRemoved(position, count);
		}

		@Override
		public int compare(Departure d1, Departure d2) {
			return d1.getTime().compareTo(d2.getTime());
		}

		@Override
		public boolean areItemsTheSame(Departure d1, Departure d2) {
			return d1.equals(d2);
		}

		@Override
		public boolean areContentsTheSame(Departure d1, Departure d2) {
			return d1.equals(d2);
		}
	});

	@Override
	public DepartureViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_departure, viewGroup, false);
		return new DepartureViewHolder(v);
	}

	@Override
	public void onBindViewHolder(final DepartureViewHolder ui, final int position) {
		final Departure dep = getItem(position);
		ui.bind(dep);
	}

	@Override
	public int getItemCount() {
		return items.size();
	}

	public void addAll(Collection<Departure> departures) {
		this.items.addAll(departures);
	}

	public Departure getItem(int position) {
		return items.get(position);
	}

	public void clear() {
		items.clear();
	}

	Date getEarliestDate() {
		if (items.size() > 0) return items.get(0).getTime();
		return new Date();
	}

}
