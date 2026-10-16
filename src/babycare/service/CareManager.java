package babycare.service;

import babycare.model.Infant;
import babycare.model.Vaccine;
import java.util.ArrayList;
import java.util.List;

public class CareManager {
    private List<Vaccine> vaccineSchedule;

    public CareManager() {
        vaccineSchedule = new ArrayList<>();
        loadData();
    }

    private void loadData() {
        vaccineSchedule.add(new Vaccine("BCG", "0", "پیشگیری از سل"));
        vaccineSchedule.add(new Vaccine("HepB (هپاتیت B)", "0", "پیشگیری از هپاتیت B"));
        vaccineSchedule.add(new Vaccine("OPV (فلج اطفال خوراکی)", "0", "پیشگیری از فلج اطفال"));
        vaccineSchedule.add(new Vaccine("پنج‌گانه (DTP+Hib+HepB)", "2", "دیفتری، کزاز، سیاه سرفه، هپاتیت B و Hib"));
        vaccineSchedule.add(new Vaccine("IPV (فلج اطفال تزریقی)", "2", "پیشگیری از فلج اطفال"));
        vaccineSchedule.add(new Vaccine("Rotavirus (روتا)", "2", "جلوگیری از اسهال شدید و کم‌آبی"));
        vaccineSchedule.add(new Vaccine("PCV", "2", "پیشگیری از عفونت‌های پنوموکوکی"));
        vaccineSchedule.add(new Vaccine("پنج‌گانه (دوز دوم)", "4", "تقویت ایمنی ۵ بیماری"));
        vaccineSchedule.add(new Vaccine("IPV (دوز دوم)", "4", "فلج اطفال تزریقی"));
        vaccineSchedule.add(new Vaccine("Rotavirus (دوز دوم)", "4", "پیشگیری از اسهال"));
        vaccineSchedule.add(new Vaccine("PCV (دوز دوم)", "4", "پیشگیری از عفونت ریه"));
        vaccineSchedule.add(new Vaccine("پنج‌گانه (دوز سوم)", "6", "تکمیل دوره ایمنی"));
        vaccineSchedule.add(new Vaccine("OPV یا IPV", "6", "فلج اطفال"));
        vaccineSchedule.add(new Vaccine("هپاتیت B", "6", "دوز سوم هپاتیت"));
        vaccineSchedule.add(new Vaccine("MMR (سرخک، سرخجه، اوریون)", "9", "پیشگیری از بیماری‌های ویروسی"));
        vaccineSchedule.add(new Vaccine("فلج اطفال", "9", "دوز تکمیلی"));
        vaccineSchedule.add(new Vaccine("MMR (نوبت اول)", "12", "سرخک + سرخجه + اوریون"));
        vaccineSchedule.add(new Vaccine("هپاتیت A", "12", "پیشگیری از بیماری کبدی"));
        vaccineSchedule.add(new Vaccine("یادآور PCV", "15", "تقویت ایمنی ریوی"));
        vaccineSchedule.add(new Vaccine("آبله مرغان (Varicella)", "15", "اختیاری / غیردولتی"));
        vaccineSchedule.add(new Vaccine("یادآور پنج‌گانه", "18", "تقویت ایمنی ۵ بیماری"));
        vaccineSchedule.add(new Vaccine("یادآور فلج اطفال", "18", "خوراکی یا تزریقی"));
    }

    public String generateReport(Infant baby) {
        StringBuilder sb = new StringBuilder();
        sb.append("گزارش وضعیت نوزاد: ").append(baby.getName()).append("\n");
        sb.append("سن: ").append(baby.getAgeInMonths()).append(" ماهه\nوزن: ").append(baby.getWeight()).append(" کیلوگرم\n");
        sb.append("----------------------------------------------------------------------\n\n");

        sb.append("[ لیست واکسن‌های مورد نیاز ]\n\n");
        boolean hasVaccine = false;
        for (Vaccine v : vaccineSchedule) {
            if (v.getAgeRange().equals(String.valueOf(baby.getAgeInMonths()))) {
                sb.append(v.getDetails()).append("\n");
                hasVaccine = true;
            }
        }
        if (!hasVaccine) sb.append(" — برای این ماه واکسن روتینی ثبت نشده است.\n");

        sb.append("\n----------------------------------------------------------------------\n");
        sb.append("[ توصیه‌های مراقبتی ]\n\n");
        
        if (baby.getAgeInMonths() < 6) {
            sb.append("– تغذیه:\n   فقط و فقط شیر مادر (تغذیه انحصاری).\n\n");
        } else {
            sb.append("– تغذیه:\n   شروع غذای کمکی نرم در کنار شیردهی.\n\n");
        }
        sb.append("– مراقبت واکسن:\n   در صورت تب بعد از واکسن، با پزشک مشورت کنید.\n\n");
        sb.append("– پایش رشد:\n   وزن نوزاد نسبت به ماه قبل بررسی شود.\n");

        return sb.toString();
    }
}