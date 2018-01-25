package projects.nyinyihtunlwin.talks.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import projects.nyinyihtunlwin.talks.R;
import projects.nyinyihtunlwin.talks.data.vo.RadioHourOnNPRVO;
import projects.nyinyihtunlwin.talks.viewholders.RadioHourOnNPRViewHolder;

/**
 * Created by Dell on 1/24/2018.
 */

public class RadioHourOnNPRAdapter extends BaseAdapter<RadioHourOnNPRViewHolder, RadioHourOnNPRVO> {

    public RadioHourOnNPRAdapter(Context context) {
        super(context);
    }

    @Override
    public RadioHourOnNPRViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_item_radio_hour_on_npr, parent, false);
        return new RadioHourOnNPRViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RadioHourOnNPRViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 16;
    }
}
