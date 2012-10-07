/*
 * This file is part of BBCT for Android.
 *
 * Copyright 2012 codeguru <codeguru@users.sourceforge.net>
 *
 * BBCT for Android is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BBCT for Android is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package bbct.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 *
 * @author codeguru <codeguru@users.sourceforge.net>
 */
public class YearFilter extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.year_filter);

        String format = this.getString(R.string.bbct_title);
        String yearFilterTitle = this.getString(R.string.year_filter_title);
        String title = String.format(format, yearFilterTitle);
        this.setTitle(title);

        this.yearText = (EditText) this.findViewById(R.id.year_filter_year_text);

        Button okButton = (Button) this.findViewById(R.id.year_filter_ok_button);
        okButton.setOnClickListener(this.onOk);

        Button cancelButton = (Button) this.findViewById(R.id.year_filter_cancel_button);
        cancelButton.setOnClickListener(this.onCancel);
    }
    private View.OnClickListener onOk = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String yearStr = YearFilter.this.yearText.getText().toString();
            if (yearStr.equals("")) {
                YearFilter.this.yearText.requestFocus();
                Toast.makeText(YearFilter.this, R.string.year_input_error, Toast.LENGTH_LONG).show();
            } else {
                int year = Integer.parseInt(yearStr);
                Intent data = new Intent();
                data.putExtra(AndroidConstants.FILTER_REQUEST_EXTRA, AndroidConstants.YEAR_FILTER_REQUEST);
                data.putExtra(AndroidConstants.YEAR_EXTRA, year);
                YearFilter.this.setResult(RESULT_OK, data);
                YearFilter.this.finish();
            }
        }
    };
    private View.OnClickListener onCancel = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            YearFilter.this.setResult(RESULT_CANCELED);
            YearFilter.this.finish();
        }
    };
    private EditText yearText = null;
}